package com.udacity.googleindiascholarships.projects.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.projects.entities.Project;

public class CreateProjectActivity extends AppCompatActivity {

    private EditText projectNameTxt;
    private EditText projectDescriptionTxt;
    private EditText projectGithubUrlTxt;
    private EditText projectLogoTxt;
    private Button saveToFirebaseBtn;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        projectNameTxt = (EditText) findViewById(R.id.project_name);
        projectDescriptionTxt =(EditText) findViewById(R.id.project_description);
        projectGithubUrlTxt = (EditText)findViewById(R.id.project_github_url);
        projectLogoTxt = (EditText)findViewById(R.id.project_logo);
        saveToFirebaseBtn = (Button) findViewById(R.id.save_to_firebase_btn);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        saveToFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(projectNameTxt.getText()) || TextUtils.isEmpty(projectNameTxt.getText()) || TextUtils.isEmpty(projectNameTxt.getText())||
                TextUtils.isEmpty(projectNameTxt.getText())|| TextUtils.isEmpty(projectNameTxt.getText())){
                    Toast.makeText(CreateProjectActivity.this, "Please Provide the details", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    Project currentProject = new Project(projectNameTxt.getText().toString(),projectDescriptionTxt.getText().toString(),projectLogoTxt.getText().toString(),projectGithubUrlTxt.getText().toString());
                    saveProjectToFirebase(currentProject);

                }
            }
        });

    }

    private void saveProjectToFirebase(Project currentProject) {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://gis-2k18.firebaseio.com");
        DatabaseReference mProjectsRef = database.getReference();
        mProjectsRef.child("projects").push().setValue(currentProject)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(CreateProjectActivity.this, "Details saved", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(CreateProjectActivity.this, "Could not be saved.Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
