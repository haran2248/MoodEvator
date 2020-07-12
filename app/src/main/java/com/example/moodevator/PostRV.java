package com.example.moodevator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

public class PostRV extends AppCompatActivity {

    TextInputEditText caption;
    Button postButton;
    Button imgUpload;
    RecyclerView postRV;
    boolean imgflag=false;
    String imgPath;
    Image image;
    StorageReference storageReference;
    PostRVAdapter postRVAdapter;
    ArrayList<Post> postArrayList;
    DatabaseReference postReference= FirebaseDatabase.getInstance().getReference().child("posts");
    DatabaseReference nameRef=FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            image = ImagePicker.getFirstImageOrNull(data);
            imgPath = image.getPath();
            imgUpload.setText(imgPath);
            imgflag = true;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_r_v);
        caption=findViewById(R.id.caption_edit_text);
        postButton=findViewById(R.id.submit_post);
        imgUpload=findViewById(R.id.upload);
        postRV=findViewById(R.id.post_RV);
        postRV.setLayoutManager(new LinearLayoutManager(this));

        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.create(PostRV.this)
                        .single()
                        .folderMode(true)
                        .start();

            }
        });
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name=snapshot.getValue(Post.class).getName();
                        Log.i("name",name);
                        postReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("post").child("name").setValue(name);
                        postReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("post").child("caption").setValue(caption.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i("error",error.getMessage());

                    }
                });

                        if(!imgflag) {
                            Toast.makeText(PostRV.this,"No photo attached",Toast.LENGTH_SHORT).show();
                            postReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("post").child("Imguri").setValue("");
                        }
                        else {
                            storageReference= FirebaseStorage.getInstance().getReference();
                            final StorageReference imgRef = storageReference.child("images");
                            final Uri file = Uri.fromFile(new File(imgPath));
                            final StorageReference myRef = imgRef.child(image.getName());

                            myRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(PostRV.this, "Image added", Toast.LENGTH_SHORT).show();
                                    myRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            postReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("post").child("Imguri").setValue(uri.toString());

                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PostRV.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

            }
        });

        postReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postArrayList=new ArrayList<Post>();
                for(DataSnapshot shot:snapshot.getChildren())
                {
                    postArrayList.add(shot.child("post").getValue(Post.class));
                }
                postRVAdapter=new PostRVAdapter(postArrayList,getApplicationContext());
                postRV.setAdapter(postRVAdapter);
                postRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(PostRV.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}