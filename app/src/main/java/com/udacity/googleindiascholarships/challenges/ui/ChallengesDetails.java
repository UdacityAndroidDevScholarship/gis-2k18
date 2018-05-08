package com.udacity.googleindiascholarships.challenges.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.challenges.entities.Challenge;
import com.udacity.googleindiascholarships.challenges.ui.adapter.ChallengesAdapter;
import com.udacity.googleindiascholarships.challenges.ui.adapter.ChallengesListAdapter;
import com.udacity.googleindiascholarships.utils.Constants;

import java.util.ArrayList;

public class ChallengesDetails extends AppCompatActivity {


    RecyclerView challengeRecyclerView;
    ArrayList<Challenge> challengeList;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mFirebaseDatabaseReference;
    ChallengesAdapter challengesAdapter;
    String child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        challengeRecyclerView = findViewById(R.id.challenge_names_list_recyclerView);
        challengeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        child = getIntent().getStringExtra("child");


        challengeList = new ArrayList<Challenge>();
        readChallengesFirebase(child);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    void  readChallengesFirebase(String child){

        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        mFirebaseDatabaseReference = mFirebaseDatabase.getReference("challenges").child(child);
        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                challengeList.clear();
                for(DataSnapshot projectSnapshot : dataSnapshot.getChildren()){

                    Challenge challenge = projectSnapshot.getValue(Challenge.class);
                    challengeList.add(challenge);

                }

                challengesAdapter = new ChallengesAdapter(getApplicationContext(), challengeList);
                challengeRecyclerView.setAdapter(challengesAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
