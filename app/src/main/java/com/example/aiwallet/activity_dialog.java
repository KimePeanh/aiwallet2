package com.example.aiwallet;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aiwallet.databinding.ActivityDialogBinding;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;



public class activity_dialog extends AppCompatActivity {

    private ActivityDialogBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        AutoCompleteTextView autoCompleteTextView = findViewById((R.id.dropdown));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currency, R.layout.drop_down_items);
        adapter.setDropDownViewResource(R.layout.drop_down_items);
        Log.d("hi", "ONstarttttt"+binding.balancebox.getText().toString());
        autoCompleteTextView.setAdapter(adapter);

    }
}