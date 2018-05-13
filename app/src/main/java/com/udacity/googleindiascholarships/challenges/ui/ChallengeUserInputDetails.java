package com.udacity.googleindiascholarships.challenges.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.challenges.entities.Upload;
import com.udacity.googleindiascholarships.currentuser.utils.RoundedImg;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChallengeUserInputDetails extends AppCompatActivity {


    EditText mName;
    EditText mDescription;
    EditText mModeratorName;
    EditText mGithubProfile;
    CircleImageView mImageView;

    Button submitFirebase;

    public String getPhotoPath;

    private Uri imageUri;


    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;

    private static final int PICK_IMAGE_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_user_input_details);


        mName = findViewById(R.id.editext_challenge_name);
        mDescription = findViewById(R.id.editext_challenge_description);
        mModeratorName = findViewById(R.id.editext_challenge_moderator);
        mGithubProfile = findViewById(R.id.editext_challenge_github);
        mImageView = findViewById(R.id.display_profile);

        submitFirebase = findViewById(R.id.submitChallenges);

        mStorageReference = FirebaseStorage.getInstance().getReference("Challeges_profile");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Challenges_profile");



        //OnClickListeners
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        submitFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadFile();

            }
        });
    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && requestCode == RESULT_OK && data != null && data.getData()!=null){
            imageUri = data.getData();
            getPhotoPath = getAbsolutePath(data.getData());
            Bitmap bitmap = BitmapFactory.decodeFile(getPhotoPath);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
            RoundedImg roundedImage = new RoundedImg(scaledBitmap);
            mImageView.setImageDrawable(roundedImage);
        }

    }

    public String getAbsolutePath(Uri uri) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void uploadFile(){

        if(imageUri !=null){
            StorageReference fileReference = mStorageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
            fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(ChallengeUserInputDetails.this,"Upload Succesfull",Toast.LENGTH_LONG).show();

                    Upload upload = new Upload(mName.getText().toString().trim(),taskSnapshot.getDownloadUrl().toString());
                    String uploadId = mDatabaseReference.push().getKey();
                    mDatabaseReference.child(uploadId).setValue(upload);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }else{
            Toast.makeText(this,"No File Added",Toast.LENGTH_LONG).show();
        }

    }
}
