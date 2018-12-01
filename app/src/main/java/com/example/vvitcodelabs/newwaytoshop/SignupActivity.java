package com.example.vvitcodelabs.newwaytoshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity {
EditText new_name,new_pass,new_user_fullname,mobileno;
RadioGroup gender;
private FirebaseAuth auth;
DatabaseReference databaseReference;
//FirebaseUser user;
String user;
String gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        new_name=findViewById(R.id.new_name);
        new_pass=findViewById(R.id.new_pass);
        new_user_fullname=findViewById(R.id.new_user_full_name);
        mobileno=findViewById(R.id.new_mobileno);
        gender=findViewById(R.id.gender);
        auth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("users");
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.male){
                    gen="male";
                }
                else if (checkedId==R.id.female){
                    gen="female";
                }
            }
        });
    }


    public void signup(View view) {
        boolean check=true;
        String name=new_name.getText().toString();
        String passwd=new_pass.getText().toString();
        String fullname=new_user_fullname.getText().toString();
        String mobilenumber=mobileno.getText().toString();
        if(name.isEmpty()) {
            new_name.setError("Email Required");
            check=false;
        }
        if(passwd.isEmpty()){
            new_pass.setError("PassWord Required");
            check=false;
        }
        if(fullname.isEmpty()) {
            new_user_fullname.setError("UserName Required");
            check=false;
        }
        if(passwd.length()<8) {
            new_pass.setError("Minimum 8 Digit Required");
            check=false;
        }
        if(mobilenumber.isEmpty()){
            mobileno.setError("MobileNO. Required");
            check=false;
        }
        if(mobilenumber.length()<10) {
            mobileno.setError("10 Digit MobileNo. Required");
            check=false;
        }

        if (check){
            Snackbar.make(view, "Make Sure your Internet must be on", Snackbar.LENGTH_LONG)
                   .setAction("Action", null).show();
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Please Wait... :) ");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
            final Model obj=new Model(name,fullname,mobilenumber,gen);
            auth.createUserWithEmailAndPassword(name,passwd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                user=auth.getCurrentUser().getUid();
                                setData(user,obj);
                                progress.dismiss();
                               // Toast.makeText(SignupActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                progress.dismiss();
                                //Log.d("yesy",task.getException().getMessage());
                                Toast.makeText(SignupActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }
    }

    private void setData(String uid, Model obj) {
        databaseReference.child(uid).setValue(obj)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(SignupActivity.this, "Account Successfully created", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{

                            Toast.makeText(SignupActivity.this, "Failed to insert", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    public void login(View view) {
        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
        finish();
    }
}
