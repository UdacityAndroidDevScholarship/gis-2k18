package com.udacity.googleindiascholarships.currentuser.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.udacity.googleindiascholarships.R;

public class ProfilePictureDisplayActivity extends AppCompatActivity {

    ImageView profilePicture;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture_display);

        profilePicture = (ImageView) findViewById(R.id.img_view_profile_pic_display);

        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("IMAGE_URI")){
                path = intent.getStringExtra("IMAGE_URI");
            }
        }

        if(path != null){
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(path), 300, 300, false);
            profilePicture.setImageBitmap(scaledBitmap);
        }

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("View Profile Picture");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
