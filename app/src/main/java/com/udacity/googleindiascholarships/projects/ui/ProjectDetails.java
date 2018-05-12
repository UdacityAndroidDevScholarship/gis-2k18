package com.udacity.googleindiascholarships.projects.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ProjectDetails extends AppCompatActivity implements View.OnClickListener {


    private String project_name;
    private String project_description;
    private String project_logo_url;
    private String project_github_url;
    private String project_status;
    TextView mProjectName;
    TextView mProjectDescription;
    ImageView mPorjectLogoImageView;
    CollapsingToolbarLayout mProjectColapsingToolbarLayout;
    Button mGithubLinkButton;
    ImageView mainLogoImageView;
    TextView mProjectStatus;
    private ImageButton shareProjectLink, markAsFavOne, markAsFavTwo;
    private static boolean markedAsFav = false;
    Uri githubUri;

    ContactAdapter contactAdapter;
    List<ContactModerator> contactList;
    RecyclerView contactRecyclerView;
    LinearLayoutManager mLinearLayoutManager;

    //Firebase Variable Declaration
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mFirebaseDatabaseReference;

    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mProjectName = findViewById(R.id.tv_projectTitleDetails);
        mProjectDescription = findViewById(R.id.tv_projectDescription);
        mPorjectLogoImageView = findViewById(R.id.iv_project_logoDetails);
        mGithubLinkButton = findViewById(R.id.projectLinkButton);
        mProjectColapsingToolbarLayout = findViewById(R.id.toolbar_layout_projectLogo);
        mainLogoImageView = findViewById(R.id.logo_ImageView);
        mProjectStatus  = findViewById(R.id.project_Status);
        shareProjectLink = findViewById(R.id.ib_share_project_url);
        markAsFavOne = findViewById(R.id.ib_one_fav_project);
        markAsFavTwo = findViewById(R.id.ib_two_fav_project);
        project_name = getIntent().getStringExtra("project_name");
        project_description = getIntent().getStringExtra("project_description");
        project_github_url = getIntent().getStringExtra("project_github_url");
        project_logo_url = getIntent().getStringExtra("project_logo_url");
        project_status = getIntent().getStringExtra("project_status");
        setTitle(project_name);
        setTitleColor(R.color.black);
        mProjectName.setText(project_name);
        mProjectDescription.setText(project_description);
        mProjectStatus.setText(project_status);
        Picasso.with(this).load(project_logo_url).into(mPorjectLogoImageView);
        Picasso.with(this).load(project_logo_url).into(mainLogoImageView);
       // mProjectColapsingToolbarLayout.set
        githubUri = Uri.parse(project_github_url);
        mGithubLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, githubUri);
                startActivity(websiteIntent);
            }
        });

        shareProjectLink.setOnClickListener(this);
        markAsFavOne.setOnClickListener(this);
        markAsFavTwo.setOnClickListener(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        contactRecyclerView = findViewById(R.id.rv_contactRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        contactRecyclerView.setLayoutManager(mLinearLayoutManager);
        contactList = new ArrayList<ContactModerator>();
        readContactFirebase(project_name);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    actionBar.setTitle(project_name);
                } else  {
                   actionBar.setTitle("");
                }
            }
        });

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

                contactAdapter = new ContactAdapter(ProjectDetails.this, contactList);
                contactRecyclerView.setAdapter(contactAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_one_fav_project:
            case R.id.ib_two_fav_project:
                if(markedAsFav){
                    Drawable emptyHeart = ContextCompat.getDrawable(this, R.drawable.ic_favorite);
                    markAsFavOne.setImageDrawable(emptyHeart);
                    markAsFavTwo.setImageDrawable(emptyHeart);
                    Toast.makeText(this, "Unmarked as favourite", Toast.LENGTH_SHORT).show();
                }else{
                    Drawable filledHeart = ContextCompat.getDrawable(this, R.drawable.ic_heart);
                    markAsFavOne.setImageDrawable(filledHeart);
                    markAsFavTwo.setImageDrawable(filledHeart);
                    Toast.makeText(this, "Marked as favourite", Toast.LENGTH_SHORT).show();
                }
                markedAsFav = !markedAsFav;
                break;
            case R.id.ib_share_project_url:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, project_github_url);
                startActivity(Intent.createChooser(shareIntent, "Share the project url"));
                break;
        }
    }
}
