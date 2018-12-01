package com.example.vvitcodelabs.newwaytoshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    ArrayList<ItemsPojo> itemsarray;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        firebaseDatabase=FirebaseDatabase.getInstance();
        myref=firebaseDatabase.getReference("items");
       // Log.i("1names",myref.toString());
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("1names","hello1");
                collectcolleges((Map<String,Object>) dataSnapshot.getValue());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private void collectcolleges(Map<String, Object> value) {
        String namevalues="";
        //Log.i("1names","hello");
        itemsarray=new ArrayList<>();
        for (Map.Entry<String, Object> entry : value.entrySet()){
            Map singleUser = (Map) entry.getValue();
            ItemsPojo cp=new ItemsPojo((String) singleUser.get("name"),(String) singleUser.get("description"),
                    (String) singleUser.get("image"),(Long) singleUser.get("price"));
            itemsarray.add(cp);
            Log.i("1names",(String) singleUser.get("name"));

        }

        if(itemsarray.size()>0) {
           recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            recyclerView.setAdapter(new AnnouncementAdapter(this, itemsarray));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public void clickedCart(MenuItem item) {
        Intent i=new Intent(this,CartActivity.class);
        startActivity(i);
    }

    public void logout(MenuItem item) {
        SharedPreferences.Editor editor = getSharedPreferences("session", MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}
