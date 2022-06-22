package com.example.aiwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aiwallet.databinding.ActivityWalletBinding;

public class wallet extends AppCompatActivity {

    private ActivityWalletBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}