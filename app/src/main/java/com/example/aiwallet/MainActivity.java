package com.example.aiwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                Intent intent = new Intent(MainActivity.this,login.class);
//                startActivity(intent);
//            }
//        }, 2000);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(MainActivity.this,home.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(MainActivity.this,login.class);
            startActivity(intent);
        }
    }

}