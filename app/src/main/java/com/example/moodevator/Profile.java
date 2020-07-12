package com.example.moodevator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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