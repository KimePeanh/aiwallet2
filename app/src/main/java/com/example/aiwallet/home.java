package com.example.aiwallet;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aiwallet.databinding.ActivityHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class home extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private FirebaseAuth mAuth;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    TextView namee;
    String username = "c";
    Map<String, Object> getuser;
    method method = new method();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        UserData();
        method.UserDa(user.getUid().toString());
        UserData();
        MaterialToolbar materialToolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.side);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        setonclick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("hi", "ONstarttttt"+username);
    }

    public void setonclick(){
        binding.wallet.setOnClickListener(v -> {
            Intent intent = new Intent(this, wallet.class);
            startActivity(intent);

        });
//        binding.navigationView.findViewById(R.id.logout).setOnClickListener(v -> {
//            mAuth.signOut();
//            Intent intent = new Intent(this, login.class);
//            startActivity(intent);
//        });
        binding.his.setOnClickListener(v -> {
            Log.d("hi", "Logout");
            mAuth.signOut();
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
        });
    }
    ImageView mIcon_val;
    public void UserData(){
        FirebaseUser user = mAuth.getCurrentUser();
        firestore.collection("Users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    documentSnapshot.getData();
                    Map<String , Object> hi = documentSnapshot.getData();
                    username = hi.get("Username").toString();
                    NavigationView navigationView = findViewById(R.id.navigation_view);
                    View headerView = navigationView.getHeaderView(0);
                    TextView navUsername = (TextView) headerView.findViewById(R.id.name_navheader);
                    navUsername.setText(username);
                    TextView navemail = (TextView) headerView.findViewById(R.id.email);
                    navemail.setText(hi.get("email").toString());
                    
                    Log.d("hi", "Exist"+username);

                }

            }
        });

    }

}