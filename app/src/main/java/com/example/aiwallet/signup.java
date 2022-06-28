package com.example.aiwallet;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.aiwallet.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Any;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class signup extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private FirebaseAuth mAuth;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String userID;
    Map<String, String> wallet = Map.of();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        setonclick();
    }

    public void setonclick(){
        binding.have.setOnClickListener(v -> {
            finish();
        });

        binding.btnSignup.setOnClickListener(v -> {
            register();
        });

    }

    private void register(){
        String name = binding.namme.getText().toString();
        String email = binding.email.getText().toString();
        String password = binding.ps.getText().toString();
        if(TextUtils.isEmpty(email)){
            binding.email.setError("Email cannot be empty!");
            binding.email.requestFocus();
        }else if (TextUtils.isEmpty((password))){
            binding.ps.setError("Password cannot be empty!");
            binding.ps.requestFocus();
        }else if (TextUtils.isEmpty((name))){
            binding.namme.setError("Username cannot be empty!");
            binding.namme.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                wallet.put("KHR", "0");
                                wallet.put("USD", "0");
                                FirebaseUser user = mAuth.getCurrentUser();
                                DocumentReference documentReference = fstore.collection("Users").document(user.getUid().toString());
                                Map<String,Object> userdata = new HashMap<>();
                                userdata.put("Username" , binding.namme.getText().toString());
                                userdata.put("email", binding.email.getText().toString());
                                userdata.put("password", binding.ps.getText().toString());
                                userdata.put("uid", user.getUid().toString());
                                userdata.put("wallet", wallet);
                                userdata.put("url", "https://firebasestorage.googleapis.com/v0/b/newonecam-53e7c.appspot.com/o/user%20(1).png?alt=media&token=c4dafa52-3e22-4514-9482-874bd4dde42d");
                                documentReference.set(userdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.w(TAG, "firebasefirestore", task.getException());
                                    }
                                });
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            }
                        }
                    });
        }
    }


}