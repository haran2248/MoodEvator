package com.example.moodevator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Details extends AppCompatActivity {

    TextInputEditText phoneNo,f1,f2,d1,loc;
    Button submit;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }


    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        attachId();
        drawerLayout=findViewById(R.id.dl);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.mood){
                    Intent intent=new Intent(Details.this,ML_face_detection.class);
                    startActivity(intent);
                }
                if(id==R.id.home_page){
                    Intent intent=new Intent(Details.this,HomePage.class);
                    startActivity(intent);
                }
                if(id==R.id.details){
                    Toast.makeText(Details.this,"this is the Edit Details Page",Toast.LENGTH_LONG).show();
                }
                if(id==R.id.help){
                    Intent intent=new Intent(Details.this,Help.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("friend2").setValue(f2.getText().toString());

                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("location").setValue(loc.getText().toString());
                if(phoneNo.getText().toString().length()==10){
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phoneNo").setValue(phoneNo.getText().toString());
                    if(f1.getText().toString().length()==10){
                        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("friend1").setValue(f1.getText().toString());
                        if(d1.getText().toString().length()==10){
                            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("doctor").setValue(d1.getText().toString());
                            Intent intent=new Intent(Details.this,HomePage.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Details.this,"Enter Doctors Valid no",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(Details.this,"Enter friend's Valid no",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(Details.this,"Enter Valid phone no",Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    private void attachId() {
        phoneNo=findViewById(R.id.phone_input_edit_text);
        f1=findViewById(R.id.friend1_input_edit_text);
        f2=findViewById(R.id.friend2_input_edit_text);
        d1=findViewById(R.id.doctor1_input_edit_text);
        loc=findViewById(R.id.location_input_edit_text);
        submit=findViewById(R.id.submit);
    }
}