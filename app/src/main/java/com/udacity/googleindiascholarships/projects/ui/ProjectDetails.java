package com.udacity.googleindiascholarships.projects.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.googleindiascholarships.R;

public class ProjectDetails extends AppCompatActivity {


    private String project_name;
    private String project_description;
    private String project_logo_url;
    private String project_github_url;


    TextView mProjectName;
    TextView mProjectDescription;
    ImageView mPorjectLogoImageView;
    CollapsingToolbarLayout mProjectColapsingToolbarLayout;
    Button mGithubLinkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        mProjectName = findViewById(R.id.tv_projectTitleDetails);
        mProjectDescription = findViewById(R.id.tv_projectDescription);
        mPorjectLogoImageView = findViewById(R.id.iv_project_logoDetails);
        mGithubLinkButton = findViewById(R.id.projectLinkButton);
        mProjectColapsingToolbarLayout = findViewById(R.id.toolbar_layout_projectLogo);




        project_name = getIntent().getStringExtra("project_name");
        project_description = getIntent().getStringExtra("project_description");
        project_github_url = getIntent().getStringExtra("project_github_url");
        project_logo_url = getIntent().getStringExtra("project_logo_url");


        mProjectName.setText(project_name);
        mProjectDescription.setText(project_description);
        Picasso.with(this).load(project_logo_url).into(mPorjectLogoImageView);
       // mProjectColapsingToolbarLayout.set

        mGithubLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri githHubUri = Uri.parse(project_github_url);
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, githHubUri);
                startActivity(websiteIntent);
            }
        });




        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
