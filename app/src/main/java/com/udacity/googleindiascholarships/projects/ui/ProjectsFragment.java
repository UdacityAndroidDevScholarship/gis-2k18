package com.udacity.googleindiascholarships.projects.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.projects.adapter.ProjectsAdapter;
import com.udacity.googleindiascholarships.projects.entities.Project;

import java.util.ArrayList;

/**
 * Created by jha.anuj.2108 on 13-04-2018.
 */

public class ProjectsFragment extends android.support.v4.app.Fragment {

    RecyclerView projectsRecyclerView;
    ArrayList<Project> projectList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_projects, container, false);

        projectsRecyclerView = rootView.findViewById(R.id.projectsRecyclerView);
        projectsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        projectList = new ArrayList<Project>();
        projectList.add(new Project("GIS APP", R.drawable.gis_placeholder));
        projectList.add(new Project("EXPLORE INDIA", R.drawable.explore_india_placeholder));
        projectList.add(new Project("QUIZ APP", R.drawable.quiz_placeholder));
        projectList.add(new Project("BLOOD DONATION", R.drawable.blooddonation_placholder));

        ProjectsAdapter projectsAdapter = new ProjectsAdapter(getContext(), projectList);
        projectsRecyclerView.setAdapter(projectsAdapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Projects");
    }
}
