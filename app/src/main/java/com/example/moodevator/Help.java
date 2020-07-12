package com.example.moodevator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Help extends AppCompatActivity {
    DatabaseReference df=FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    TextInputEditText help;
    String name,location,phoneNo;
    ArrayList<Info> infoArrayList;
    RecyclerView helpRV;
    HelperAdapter adapter;
    Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        help=findViewById(R.id.help_input_edit_text);
        sub=findViewById(R.id.submit);
        helpRV=findViewById(R.id.help_rv);
        helpRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                df.child("helpDetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Info").child("help").setValue(help.getText().toString());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name=snapshot.getValue(Info.class).getName();

                        location=snapshot.getValue(Info.class).getLocation();
                        phoneNo=snapshot.getValue(Info.class).getPhoneNo();
                        df.child("helpDetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Info").child("name").setValue(name);
                        df.child("helpDetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Info").child("location").setValue(location);
                        df.child("helpDetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Info").child("phoneNo").setValue(phoneNo);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("error", String.valueOf(error));
                    }
                });


            }
        });
        df.child("helpDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                infoArrayList=new ArrayList<Info>();
                for(DataSnapshot shot:snapshot.getChildren())
                {
                    infoArrayList.add(shot.child("Info").getValue(Info.class));
                    //Log.i("nameHelp",shot.getValue(Info.class).getName());
                    //Log.i("phoneHelp",shot.getValue(Info.class).getPhoneNo());
                    //Log.i("locHelp",shot.getValue(Info.class).getLocation());
                }
                adapter=new HelperAdapter(infoArrayList,getApplicationContext());
                helpRV.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}