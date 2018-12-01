package com.example.vvitcodelabs.newwaytoshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText uname,pass;
    private FirebaseAuth lauth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String fullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=findViewById(R.id.uname);
        pass=findViewById(R.id.pass);
        lauth = FirebaseAuth.getInstance();
        sharedPreferences=getSharedPreferences("session",MODE_PRIVATE);

    }
    public void submit(View view) {

        final String name=uname.getText().toString();
        final String passwd=pass.getText().toString();
        if(name.isEmpty()) {
            uname.setError("UserName Required");
        }
        if(passwd.isEmpty())
            pass.setError("PassWord Required");
        if(passwd.length()<8) {
            pass.setError("Minimum 8 Digit Required");
        }
        else{
            Snackbar.make(view, "  Make Sure your Internet must be on ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Please Wait...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();

            //intent.putExtra("name",name);
            lauth.signInWithEmailAndPassword(name,passwd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               progress.dismiss();
                               Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                               editor=sharedPreferences.edit();
                               editor.putInt("user",1);
                               editor.putString("name",name);
                               editor.apply();
                               intent.putExtra("name",name);
                               startActivity(intent);
                               finish();
                           }
                           else{
                               progress.dismiss();
                               Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
            }
    }

    public void signup(View view) {

        startActivity(new Intent(LoginActivity.this,SignupActivity.class));
        finish();
    }
}
