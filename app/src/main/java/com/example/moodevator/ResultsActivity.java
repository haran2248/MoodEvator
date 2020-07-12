package com.example.moodevator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_LONG;

public class ResultsActivity extends AppCompatActivity {
    TextView awake,smiling,result,friend,doctor;
    Button doctor_button;
    Button friend_button;
    DatabaseReference contactRef= FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    String friend_no,doctor_no;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        awake=findViewById(R.id.awake);
        smiling=findViewById(R.id.smile);
        result=findViewById(R.id.results);

        String awak=getIntent().getExtras().getString("eyesOpenProbability");

        String smile=getIntent().getExtras().getString("smilingProbability");
        float sm=Float.valueOf(smile);
        float aw=Float.valueOf(awak);
        awake.setText(awak);
        smiling.setText(smile);
        if(sm>0.5)
        {
            result.setText("Beautiful smile,Keep smiling and stay happy :)!!");
        }
        else{
            result.setText("Pls smile more!,Contact your friends and loved ones :)");



        }
    }
}