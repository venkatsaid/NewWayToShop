package com.example.vvitcodelabs.newwaytoshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    ArrayList<ItemsPojo> itemsarray;
    RecyclerView recyclerView;
    long p=0;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView=findViewById(R.id.recycler1);
        t=findViewById(R.id.p);
        firebaseDatabase=FirebaseDatabase.getInstance();
        myref=firebaseDatabase.getReference("cart");
        // Log.i("1names",myref.toString());
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("1names","hello1");
                if(dataSnapshot.exists()){
                collectcolleges((Map<String,Object>) dataSnapshot.getValue());}
                else {
                    Toast.makeText(CartActivity.this, "no items added", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private void collectcolleges(Map<String, Object> value) {
        String namevalues="";
        //Log.i("1names","hello");
        p=0;
        itemsarray=new ArrayList<>();
        for (Map.Entry<String, Object> entry : value.entrySet()){
            Map singleUser = (Map) entry.getValue();
            ItemsPojo cp=new ItemsPojo((String) singleUser.get("name"),(String) singleUser.get("description"),
                    (String) singleUser.get("image"),(Long) singleUser.get("price"));
            itemsarray.add(cp);
            Log.i("1names",(String) singleUser.get("name"));
            p=p+(Long) singleUser.get("price");

        }

        if(itemsarray.size()>0) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            recyclerView.setAdapter(new AnnouncementAdapter(this, itemsarray));
            t.setText("Total cart price: "+String.valueOf(p));
        }

    }
}
