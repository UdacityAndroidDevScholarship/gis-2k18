package com.udacity.googleindiascholarships.challenges.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.challenges.ui.adapter.ChallengesAdapter;
import com.udacity.googleindiascholarships.challenges.entities.Challenge;
import com.udacity.googleindiascholarships.challenges.ui.adapter.ChallengesListAdapter;
import com.udacity.googleindiascholarships.projects.entities.Project;
import com.udacity.googleindiascholarships.projects.ui.adapter.ProjectsAdapter;
import com.udacity.googleindiascholarships.utils.Constants;

import java.util.ArrayList;

/**
 * Created by jha.anuj.2108 on 13-04-2018.
 */

public class ChallengesFragment extends android.support.v4.app.Fragment {

    RecyclerView challengeRecyclerView;
    ArrayList<Challenge> challengeList;
    ChallengesListAdapter challengesAdapter;


    //Firebase Variable Declaration
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mFirebaseDatabaseReference;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_challenges, container, false);


        challengeRecyclerView = rootView.findViewById(R.id.challenges_recyclerView);
        challengeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        challengeList = new ArrayList<Challenge>();

       readChallengesFirebase();

        return rootView;
    }


    void  readChallengesFirebase(){

        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        mFirebaseDatabaseReference = mFirebaseDatabase.getReference("challenges").child("challenge_list");
        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                challengeList.clear();
                for(DataSnapshot projectSnapshot : dataSnapshot.getChildren()){

                    Challenge challenge = projectSnapshot.getValue(Challenge.class);
                    challengeList.add(challenge);

                }

                challengesAdapter = new ChallengesListAdapter(getContext(), challengeList);
                challengeRecyclerView.setAdapter(challengesAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Challenges");
    }
}
