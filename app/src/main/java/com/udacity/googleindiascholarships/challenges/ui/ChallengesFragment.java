package com.udacity.googleindiascholarships.challenges.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.challenges.ui.adapter.ChallengesAdapter;
import com.udacity.googleindiascholarships.challenges.entities.Challenge;

import java.util.ArrayList;

/**
 * Created by jha.anuj.2108 on 13-04-2018.
 */

public class ChallengesFragment extends android.support.v4.app.Fragment {

    RecyclerView challengeRecyclerView;
    ArrayList<Challenge> challengeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_challenges, container, false);

        challengeRecyclerView = rootView.findViewById(R.id.challenges_recyclerView);
        challengeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        challengeList = new ArrayList<Challenge>();
        challengeList.add(new Challenge("Akshit Jain"));
        challengeList.add(new Challenge("Rahul"));
        challengeList.add(new Challenge("Vineet"));
        challengeList.add(new Challenge("Anuj"));

        ChallengesAdapter challengesAdapter = new ChallengesAdapter(getContext(), challengeList);
        challengeRecyclerView.setAdapter(challengesAdapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Challenges");
    }
}
