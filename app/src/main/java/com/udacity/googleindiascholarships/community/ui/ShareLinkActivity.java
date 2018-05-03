package com.udacity.googleindiascholarships.community.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.community.ui.entities.ExternalLinks;
import com.udacity.googleindiascholarships.projects.entities.Project;
import com.udacity.googleindiascholarships.projects.ui.CreateProjectActivity;
import com.udacity.googleindiascholarships.utils.Constants;

public class ShareLinkActivity extends AppCompatActivity {
    private EditText linkUrlTxt;
    private EditText linkDescriptionTxt;
    private EditText linkSharedByTxt;
    private Button saveToFirebaseBtn;
    private ProgressBar progressBar;
    private Spinner dropdown;
    private String linkType;
    private String TAG = "ShareLinkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_link);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        linkUrlTxt = (EditText) findViewById(R.id.link_url);
        linkDescriptionTxt = (EditText) findViewById(R.id.link_description);
        linkSharedByTxt = (EditText) findViewById(R.id.link_shared_by);
        dropdown = (Spinner) findViewById(R.id.blog_or_resources_spinner);
        String[] items = new String[]{"Blog", "Resource"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        saveToFirebaseBtn = (Button) findViewById(R.id.save_to_firebase_btn);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        saveToFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkType = dropdown.getSelectedItem().toString();
                if (TextUtils.isEmpty(linkUrlTxt.getText()) || TextUtils.isEmpty(linkSharedByTxt.getText()) || TextUtils.isEmpty(linkDescriptionTxt.getText()) || TextUtils.isEmpty(linkType)) {
                    Toast.makeText(ShareLinkActivity.this, "Please provide all details", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    ExternalLinks currentLink = new ExternalLinks(linkUrlTxt.getText().toString(), linkSharedByTxt.getText().toString(),linkDescriptionTxt.getText().toString());
                    saveLinkToFirebase(currentLink);

                }
            }
        });


    }

    private void saveLinkToFirebase(ExternalLinks currentLink) {
        FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        DatabaseReference mExternalResourcesRef = database.getReference();

        if (linkType.equalsIgnoreCase("blog")) {
            mExternalResourcesRef.child("external_resources").child("blogs").push().setValue(currentLink)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ShareLinkActivity.this, "Details saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Log.i(TAG, "onFailure: "+e.getMessage());
                            Toast.makeText(ShareLinkActivity.this, "Could not be saved.Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            mExternalResourcesRef.child("external_resources").child("resources").push().setValue(currentLink)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ShareLinkActivity.this, "Details saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Log.i(TAG, "onFailure: "+e.getMessage());
                            Toast.makeText(ShareLinkActivity.this, "Could not be saved.Check your internet connection", Toast.LENGTH_SHORT).show();
                        }

                    });
        }
    }

}
