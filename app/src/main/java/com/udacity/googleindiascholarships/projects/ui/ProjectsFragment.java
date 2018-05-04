package com.udacity.googleindiascholarships.projects.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.community.ui.entities.ExternalLinks;
import com.udacity.googleindiascholarships.projects.ui.adapter.ProjectsAdapter;
import com.udacity.googleindiascholarships.projects.entities.Project;
import com.udacity.googleindiascholarships.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ProjectsFragment extends android.support.v4.app.Fragment {




    RecyclerView projectsRecyclerView;
    List<Project> projectList;
    ProjectsAdapter projectsAdapter;
    FloatingActionButton createProjectBtn;

    //Firebase Variable Declaration
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mFirebaseDatabaseReference;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_projects, container, false);

        projectsRecyclerView = rootView.findViewById(R.id.projectsRecyclerView);
        createProjectBtn = rootView.findViewById(R.id.create_projects_fab_btn);
        projectList = new ArrayList<Project>();
        projectsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        createProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CreateProjectActivity.class);
                startActivity(intent);
            }
        });

       /* projectList = new ArrayList<Project>();
        projectList.add(new Project("GIS APP", R.drawable.gis_placeholder));
        projectList.add(new Project("EXPLORE INDIA", R.drawable.explore_india_placeholder));
        projectList.add(new Project("QUIZ APP", R.drawable.quiz_placeholder));
        projectList.add(new Project("BLOOD DONATION", R.drawable.blooddonation_placholder));*/

        readProjectsFirebase();

        return rootView;
    }


    void readProjectsFirebase(){

        mFirebaseDatabase = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        mFirebaseDatabaseReference = mFirebaseDatabase.getReference("projects");
        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                projectList.clear();
                for(DataSnapshot projectSnapshot : dataSnapshot.getChildren()){

                    Project project = projectSnapshot.getValue(Project.class);
                    projectList.add(project);

                }

                projectsAdapter = new ProjectsAdapter(getContext(), projectList);
                projectsRecyclerView.setAdapter(projectsAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Projects");
    }
}
