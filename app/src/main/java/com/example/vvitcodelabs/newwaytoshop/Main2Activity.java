package com.example.vvitcodelabs.newwaytoshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
ImageView imageView;
TextView name,price,desc;
Button add,rcart,payment;
ItemsPojo itemsPojo;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference,d1;
static boolean check=false;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=findViewById(R.id.itemName);
        price=findViewById(R.id.itemPrice);
        imageView=findViewById(R.id.itemimage1);
        desc=findViewById(R.id.desc);
        add=findViewById(R.id.addcart);
        rcart=findViewById(R.id.rcart);
        payment=findViewById(R.id.payment);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("cart");
        itemsPojo=new ItemsPojo();
        name.setText(getIntent().getStringExtra("name"));
        price.setText(getIntent().getStringExtra("price"));
        desc.setText(getIntent().getStringExtra("desc"));
        s=getIntent().getStringExtra("name");
        /*if (check){
            add.setText("Remove item");
        }
        else {
            add.setText("Add to cart");
        }*/
        itemsPojo.setDescription(getIntent().getStringExtra("desc"));
        itemsPojo.setImage(getIntent().getStringExtra("image"));
        itemsPojo.setName(getIntent().getStringExtra("name"));
        itemsPojo.setPrice(Long.parseLong(getIntent().getStringExtra("price")));
        Log.i("name",getIntent().getStringExtra("desc"));
        Picasso.with(this).load(getIntent().getStringExtra("image")).error(R.mipmap.ic_launcher).into(imageView);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!check)
                Toast.makeText(Main2Activity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                 databaseReference.push().setValue(itemsPojo);
            }
        });
        rcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "removed from cart", Toast.LENGTH_SHORT).show();
                d1=firebaseDatabase.getReference();
                Query applesQuery = d1.child("cart").orderByChild("name").equalTo(s);

                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                            /*if(!dataSnapshot.hasChildren()){
                                Intent i=new Intent(Main2Activity.this,MainActivity.class);
                                startActivity(i);
                            }*/
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Main2Activity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
