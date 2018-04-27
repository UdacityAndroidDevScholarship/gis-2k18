package com.udacity.googleindiascholarships.members.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by vinee_000 on 27-04-2018.
 */

public class ProfileProjectsAdapter extends RecyclerView.Adapter<ProfileProjectsAdapter.ProjectsViewHolder> {

    class ProjectsViewHolder extends RecyclerView.ViewHolder{
        public ProjectsViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_profile_projects,parent,false);
        return new ProjectsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProjectsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
