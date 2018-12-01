package com.example.vvitcodelabs.newwaytoshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
TextView t1,t2;
ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        img1=findViewById(R.id.img1);
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.fade1);
        t1.startAnimation(animation);
        t2.startAnimation(animation);
        img1.startAnimation(animation);
        sharedPreferences=getSharedPreferences("session",MODE_PRIVATE);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPreferences.contains("user")){
                    if(sharedPreferences.getInt("user",0)==1){
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                    }
                    else {
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finish();
                    }
            }
                else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }

            }
        },3000);
    }
}
