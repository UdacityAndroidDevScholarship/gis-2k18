package com.udacity.googleindiascholarships.projects.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.projects.entities.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsCardViewHolder> {

    public Context mContext;
    public List<Project> mListItems;
    public ArrayList<Project> mProjectListItems;

    public ProjectsAdapter(Context context, List<Project> mListItems) {
        mContext = context;
        this.mListItems = mListItems;
    }

    @Override
    public ProjectsCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_projects, parent, false);
        return new ProjectsCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProjectsCardViewHolder holder, int position) {
        final Project listItem = mListItems.get(position);
        holder.projectNameTextView.setText(listItem.getName());
        holder.projectImageView.setImageResource(listItem.getPlaceHolderImage());

    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public class ProjectsCardViewHolder extends RecyclerView.ViewHolder {

        public TextView projectNameTextView;
        public ImageView projectImageView;


        public ProjectsCardViewHolder(View itemView) {
            super(itemView);
            projectImageView = (ImageView) itemView.findViewById(R.id.projectImage);
            projectNameTextView = (TextView) itemView.findViewById(R.id.projectName);

        }
    }

}