package com.udacity.googleindiascholarships.members.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.members.ui.adapters.EditProfileProjectsAdapter;
import com.udacity.googleindiascholarships.members.ui.adapters.ProfileProjectsAdapter;

/**
 * Created by HP on 05-05-2018.
 */

public class EditProfileProjectsTabFragment extends Fragment {

    LinearLayoutManager llmProfileProjects;
    RecyclerView rvProfileProjects;
    EditProfileProjectsAdapter profileProjectsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View customView = inflater.inflate(R.layout.fragment_edit_profile_projects_tab, container, false);

        rvProfileProjects = (RecyclerView) customView.findViewById(R.id.rv_edit_profile_projects);

        llmProfileProjects = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvProfileProjects.setLayoutManager(llmProfileProjects);

        profileProjectsAdapter = new EditProfileProjectsAdapter();
        rvProfileProjects.setAdapter(profileProjectsAdapter);




        return customView;
    }

}
