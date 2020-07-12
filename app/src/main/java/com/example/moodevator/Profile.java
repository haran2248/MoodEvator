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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    Button logout;
    TextView name,friend1,doctor;
    GoogleSignInOptions gso;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    GoogleSignInClient googleSignInClient;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        drawerLayout=findViewById(R.id.dl);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(R.id.navigation);
        View view=navigationView.getHeaderView(0);
        final TextView namer=view.findViewById(R.id.username);

        DatabaseReference user= FirebaseDatabase.getInstance().getReference().child("users");
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                namer.setText(snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(Info.class).getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.mood){
                    Intent intent=new Intent(Profile.this,ML_face_detection.class);
                    startActivity(intent);

                }
                if(id==R.id.logout){
                    Toast.makeText(Profile.this,"this is the HomePage",Toast.LENGTH_LONG).show();

                }
                if(id==R.id.details){
                    Intent intent=new Intent(Profile.this,Details.class);
                    startActivity(intent);
                }
                if(id==R.id.help){
                    Intent intent=new Intent(Profile.this,Help.class);
                    startActivity(intent);
                }
                if(id==R.id.posts){
                    Intent intent=new Intent(Profile.this,PostRV.class);
                    startActivity(intent);
                }
                if(id==R.id.home_page){

                    Intent next=new Intent(Profile.this, HomePage.class);
                    startActivity(next);

                }
                return true;
            }
        });

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(this, gso);
        logout=findViewById(R.id.logout);
        name=findViewById(R.id.name);
        friend1=findViewById(R.id.friend1);
        doctor=findViewById(R.id.doctor1);
        DatabaseReference databaseReference =FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.getValue(Info.class).getName());
                friend1.setText(snapshot.getValue(Info.class).getFriend1());
                doctor.setText(snapshot.getValue(Info.class).getDoctor());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.logout:
                        signOut();

                        break;
                    // ...
                }

            }
        });
    }

    private void signOut() {

        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Profile.this,"signed out",Toast.LENGTH_SHORT);
                        finish();

                    }
                });
        Intent next=new Intent(Profile.this, MainActivity.class);
        startActivity(next);
    }
}