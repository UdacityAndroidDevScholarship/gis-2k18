package com.udacity.googleindiascholarships.projects.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.projects.entities.Project;
import com.udacity.googleindiascholarships.projects.ui.ProjectDetails;

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
        holder.projectNameTextView.setText(listItem.getProject_name());
        //holder.projectImageView.setImageResource(listItem.getPlaceHolderImage());
        Picasso.with(mContext).load(listItem.getProject_logo_url()).into(holder.projectImageView);

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



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    int position = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), ProjectDetails.class);
                    intent.putExtra("project_github_url",mListItems.get(position).getProject_github_url());
                    intent.putExtra("project_description",mListItems.get(position).getProject_description());
                    intent.putExtra("project_logo_url",mListItems.get(position).getProject_logo_url());
                    intent.putExtra("project_name",mListItems.get(position).getProject_name());
                    context.startActivity(intent);

                }
            });



        }
    }

}