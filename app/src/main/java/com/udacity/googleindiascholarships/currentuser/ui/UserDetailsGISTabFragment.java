package com.udacity.googleindiascholarships.currentuser.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.currentuser.ui.adapters.UserDetailsGISProjectsAdapter;
import com.udacity.googleindiascholarships.currentuser.ui.adapters.UserDetailsGISStoriesAdapter;
/**
 * Created by Sudhanshu on 05-05-2018.
 */

public class UserDetailsGISTabFragment extends Fragment {

    public UserDetailsGISTabFragment() {
        // Required empty public constructor
    }


    RecyclerView rvProfileGISStories,rvProfileGISProjects;
    LinearLayoutManager llmProfileGISStories;
    GridLayoutManager glmProfileGISProjects;
    UserDetailsGISStoriesAdapter profileGISStoriesAdapter;
    UserDetailsGISProjectsAdapter profileGISProjectsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View customView = inflater.inflate(R.layout.fragment_profile_gistab, container, false);

        rvProfileGISStories = (RecyclerView) customView.findViewById(R.id.rv_profile_gis_stories);
        rvProfileGISProjects = (RecyclerView) customView.findViewById(R.id.rv_profile_gis_projects);

        llmProfileGISStories = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvProfileGISStories.setLayoutManager(llmProfileGISStories);

        profileGISStoriesAdapter = new UserDetailsGISStoriesAdapter();
        rvProfileGISStories.setAdapter(profileGISStoriesAdapter);


        glmProfileGISProjects = new GridLayoutManager(getContext(),2);
        rvProfileGISProjects.setLayoutManager(glmProfileGISProjects);

        profileGISProjectsAdapter = new UserDetailsGISProjectsAdapter(getContext());
        rvProfileGISProjects.setAdapter(profileGISProjectsAdapter);



        return customView;
    }

}
