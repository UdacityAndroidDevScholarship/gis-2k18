package com.udacity.googleindiascholarships.projects.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.projects.entities.Project;
import com.udacity.googleindiascholarships.projects.ui.adapter.ContactAdapter;
import com.udacity.googleindiascholarships.projects.ui.adapter.ProjectsAdapter;

import java.util.ArrayList;


public class ContactFragment extends Fragment {


    public ContactFragment() {
        // Required empty public constructor
    }

    ArrayList<Project> contactList;
    RecyclerView contactRecyclerView;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        contactRecyclerView = rootView.findViewById(R.id.rv_contactRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        contactRecyclerView.setLayoutManager(mLinearLayoutManager);

        contactList = new ArrayList<Project>();
        contactList.add(new Project("GIS APP", R.drawable.gis_placeholder));
        contactList.add(new Project("EXPLORE INDIA", R.drawable.explore_india_placeholder));
        contactList.add(new Project("QUIZ APP", R.drawable.quiz_placeholder));
        contactList.add(new Project("BLOOD DONATION", R.drawable.blooddonation_placholder));

        ContactAdapter projectsAdapter = new ContactAdapter(getContext(),contactList);
        contactRecyclerView.setAdapter(projectsAdapter);
        return rootView;
    }


}
