//package com.example.aiwallet;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//
//import com.example.aiwallet.databinding.ActivityWalletBinding;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.Map;
//
//public class wallet extends AppCompatActivity {
//
//    private ActivityWalletBinding binding;
//    Map<String, String> wallet = Map.of();
//    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//    private FirebaseAuth mAuth;
//
//    FirebaseAuth auth;
//    FirebaseFirestore fstore;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityWalletBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        Log.d("hi","exis" + user.getUid());
//        getwallet();
//
//
//    }
//
//
//
//    public  void  getwallet(){
//        FirebaseUser user = mAuth.getCurrentUser();
//        firestore.collection("Users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if(documentSnapshot.exists()){
//                    documentSnapshot.getData();
//                    Map<String , Object> hi = documentSnapshot.getData();
//                    wallet = (Map<String, String>) hi.get("wallet");
//                    binding.khramount.setText("KHR "+ wallet.get("KHR").toString());
//                    binding.usddamount.setText("USD "+wallet.get("USD").toString());
//                    Log.d("hi","exis" + hi.get("wallet"));
//                    Log.d("hi","exis" + wallet);
//                }
//            }
//
//        });
//    }
//}

package com.example.aiwallet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aiwallet.databinding.ActivityDialogBinding;
import com.example.aiwallet.databinding.ActivityWalletBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;


public class wallet extends AppCompatActivity {

    private ActivityWalletBinding binding;
    ImageButton button;
    String choose;
    Dialog dialog;
    TextView cu;
    EditText amount;
    Map<String, String> wallet = Map.of();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    FirebaseAuth auth;
    FirebaseFirestore fstore;
    FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
       user = mAuth.getCurrentUser();

        binding.addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
       getwallet();
    }

    public void showDialog(){

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        cu = dialog.findViewById(R.id.dropdown);
        amount = dialog.findViewById(R.id.balancebox);
        RelativeLayout doneButton = dialog.findViewById(R.id.done_button);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateamount(cu.getText().toString());
                dialog.dismiss();
                Log.d("hi", "8888ttt"+cu.getText().toString());

            }

        });

        AutoCompleteTextView autoCompleteTextView = dialog.findViewById(R.id.dropdown);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.currency, R.layout.drop_down_items);
        adapter.setDropDownViewResource(R.layout.drop_down_items);
        adapter.notifyDataSetChanged();
        autoCompleteTextView.setAdapter(adapter);

        choose = cu.getText().toString();
        dialog.show();
        Log.d("hi", "ONstarttttt"+autoCompleteTextView.getText().toString());
//        if (autoCompleteTextView.getText().toString() == "USD"){
//            choose = "USD";
//        }else {
//            choose = "KHR";
//        }
        Log.d("hi", "yhjh"+choose);
    }

       public  void  getwallet(){
        FirebaseUser user = mAuth.getCurrentUser();
        firestore.collection("Users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    documentSnapshot.getData();
                    Map<String , Object> hi = documentSnapshot.getData();
                    wallet = (Map<String, String>) hi.get("wallet");
                    binding.khramount.setText("KHR "+ wallet.get("KHR").toString());
                    binding.usddamount.setText("USD "+wallet.get("USD").toString());
                    Log.d("hi","exis" + hi.get("wallet"));
                    Log.d("hi","exis" + wallet);
                }
            }

        });
    }

    public void updateamount(String currency){
        String u = binding.usddamount.getText().toString();
        String a = "100";
        Log.d("hi","888888888888888");
        Log.d("hi","888888888888888888"+ binding.usddamount.getText().toString());
        getwallet();
        Log.d("hi","99999999999999999999999999");
        if (currency.equals("USD")){
            Log.d("hi","intupdateamont" + wallet);
            Log.d("hi","888888888888888888");
            Log.d("hi",amount.getText().toString());
//
            if (amount.getText().toString().isEmpty()){
                Log.d("hi","0000000000000");
            }else {
                Double b;
                Double c;
                Double result;
                c = Double.parseDouble(amount.getText().toString());
                b = Double.parseDouble(wallet.get("USD").toString());

                result = b+c;
//            double usd;
//           usd  =  Double.parseDouble(binding.usddamount.getText().toString()) + Double.parseDouble(amount.getText().toString());
              Log.d("hi","YESSS" + result);
              wallet.remove("USD");
                Log.d("hi","YESSS" + wallet);
                wallet.put("USD", result.toString());
                fstore.collection("Users").document(user.getUid().toString()).update("wallet", wallet);

            }
        }else {
            if (amount.getText().toString().isEmpty()){
                Log.d("hi","0000000000000");
            }else {
                Double b;
                Double c;
                Double result;
                c = Double.parseDouble(amount.getText().toString());
                b = Double.parseDouble(wallet.get("KHR").toString());

                result = b+c;
//            double usd;
//           usd  =  Double.parseDouble(binding.usddamount.getText().toString()) + Double.parseDouble(amount.getText().toString());
                Log.d("hi","YESSS" + result);
                wallet.remove("KHR");
//                Log.d("hi","YESSS" + wallet);
                wallet.put("KHR", result.toString());
               fstore.collection("Users").document(user.getUid().toString()).update("wallet", wallet);

            }
        }
    }

}
