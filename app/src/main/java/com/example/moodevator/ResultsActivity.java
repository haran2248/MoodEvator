package com.example.moodevator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {
    TextView awake,smiling,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        awake=findViewById(R.id.awake);
        smiling=findViewById(R.id.smile);
        result=findViewById(R.id.results);
        String awak=getIntent().getExtras().getString("eyesOpenProbability");
        Log.i("awakinResults",awak);
        String smile=getIntent().getExtras().getString("smilingProbability");
        Log.i("smileinResults",smile);
        awake.setText(awak);
        smiling.setText(smile);
        float sm=Float.valueOf(smile);
        if(sm>0.5)
        {
            result.setText("Beautiful smile,Keep smiling and stay happy :)!!");
        }
        else{
            result.setText("Pls smile more!,Contact your friends and loved ones :)");

        }
    }
}