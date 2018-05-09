package com.udacity.googleindiascholarships.projects.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.projects.entities.ContactModerator;
import com.udacity.googleindiascholarships.projects.ui.adapter.ContactAdapter;
import com.udacity.googleindiascholarships.utils.Constants;

import java.util.ArrayList;
import java.util.List;

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


    ContactAdapter contactAdapter;
    List<ContactModerator> contactList;
    RecyclerView contactRecyclerView;
    LinearLayoutManager mLinearLayoutManager;

    //Firebase Variable Declaration
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mFirebaseDatabaseReference;


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

        contactRecyclerView = findViewById(R.id.rv_contactRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        contactRecyclerView.setLayoutManager(mLinearLayoutManager);
        contactList = new ArrayList<ContactModerator>();

        readContactFirebase(project_name);






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





    void readContactFirebase(String child){

        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        mFirebaseDatabaseReference = mFirebaseDatabase.getReference("project_moderator").child(child);
        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactList.clear();
                for(DataSnapshot contactSnapshot : dataSnapshot.getChildren()){
                    ContactModerator contact = contactSnapshot.getValue(ContactModerator.class);
                    contactList.add(contact);


                }

                contactAdapter = new ContactAdapter(getApplicationContext(), contactList);
                contactRecyclerView.setAdapter(contactAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



}
