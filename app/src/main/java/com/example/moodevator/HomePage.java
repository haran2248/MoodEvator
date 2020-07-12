package com.example.moodevator;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;

public class HomePage extends AppCompatActivity {

    ImageView m1,m2,m3,m4,m5;
    ImageView s1,s2,s3,s4,s5;
    ImageView b1,b2,b3,b4,b5;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        googleSignInOptions =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions);

        m1=findViewById(R.id.pursuit);
        m2=findViewById(R.id.shawshank);
        m3=findViewById(R.id.blind_side);
        m4=findViewById(R.id.forrest_gump);
        m5=findViewById(R.id.four_min);
        s1=findViewById(R.id.linkin);
        s2=findViewById(R.id.green_day);
        s3=findViewById(R.id.one_d);
        s4=findViewById(R.id.ed);
        s5=findViewById(R.id.one_republic);
        b1=findViewById(R.id.you_can_win);
        b2=findViewById(R.id.think_and_grow);
        b3=findViewById(R.id.power_of_sub);
        b4=findViewById(R.id.wings_of_fire);
        b5=findViewById(R.id.attitude);

        drawerLayout=findViewById(R.id.dl);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(R.id.navigation);
        View view=navigationView.getHeaderView(0);
        final TextView name=view.findViewById(R.id.username);
        DatabaseReference user= FirebaseDatabase.getInstance().getReference().child("users");
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(Info.class).getName());
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
                    Intent intent=new Intent(HomePage.this,ML_face_detection.class);
                    startActivity(intent);

                }
                if(id==R.id.home_page){
                    Toast.makeText(HomePage.this,"this is the HomePage",Toast.LENGTH_LONG).show();

                }
                if(id==R.id.details){
                    Intent intent=new Intent(HomePage.this,Details.class);
                    startActivity(intent);
                }
                if(id==R.id.help){
                    Intent intent=new Intent(HomePage.this,Help.class);
                    startActivity(intent);
                }
                if(id==R.id.posts){
                    Intent intent=new Intent(HomePage.this,PostRV.class);
                    startActivity(intent);
                }
                if(id==R.id.logout){

                    Intent next=new Intent(HomePage.this, Profile.class);
                    startActivity(next);

                }
                return true;
            }
        });
        m1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,m1, ViewCompat.getTransitionName(m1));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.pursuit); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        m2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,m2, ViewCompat.getTransitionName(m2));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.shawshank); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        m3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,m3, ViewCompat.getTransitionName(m3));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.blind_side); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        m4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,m4, ViewCompat.getTransitionName(m4));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.forrest_gump); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        m5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,m5, ViewCompat.getTransitionName(m5));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.four_min); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        s1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,s1, ViewCompat.getTransitionName(s1));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.linkin_park); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,s2, ViewCompat.getTransitionName(s2));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.greenday); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,s3, ViewCompat.getTransitionName(s3));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.one_direction); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,s4, ViewCompat.getTransitionName(s4));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.ed_sheeran); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,s5, ViewCompat.getTransitionName(s5));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.one_republic); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,b1, ViewCompat.getTransitionName(b1));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.you_can_win); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,b2, ViewCompat.getTransitionName(b2));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.think_rich); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,b3, ViewCompat.getTransitionName(b3));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.power_of_sub); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,b4, ViewCompat.getTransitionName(b4));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.wings_of_fire); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ExpandedDetails.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomePage.this,b5, ViewCompat.getTransitionName(b5));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.attitude); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });




    }
}