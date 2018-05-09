package com.udacity.googleindiascholarships.currentuser.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.udacity.googleindiascholarships.R;

/**
 * Created by Sudhanshu on 07-05-2018.
 */

public class UserDetailsGISProjectsAdapter extends
        RecyclerView.Adapter<UserDetailsGISProjectsAdapter.UserDetailsGISProjectsViewHolder>{

    Context mContext;
    private static int mListSize = 10;
    private static final int VIEW_TYPE_ADD_GIS_PROJECT = 0;
    private static final int VIEW_TYPE_GIS_PROJECT = 1;

    public UserDetailsGISProjectsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    class  UserDetailsGISProjectsViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileGISProjects;
        UserDetailsGISProjectsViewHolder(View itemView) {
            super(itemView);
            ivProfileGISProjects = (ImageView) itemView.findViewById(R.id.iv_profile_gis_project_image);

        }
    }
    @Override
    public UserDetailsGISProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.list_item_profile_gis_projects;
        switch (viewType){
            case VIEW_TYPE_ADD_GIS_PROJECT:
                layoutId = R.layout.list_item_add_gis_project;
                break;
            case VIEW_TYPE_GIS_PROJECT:
                layoutId = R.layout.list_item_profile_gis_projects;
                break;
        }
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        return new UserDetailsGISProjectsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserDetailsGISProjectsViewHolder holder, int position) {
        if(position != mListSize - 1) {
            if (position % 2 == 0) {
                holder.ivProfileGISProjects.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sample_profile_gis_project_1));
            } else {
                holder.ivProfileGISProjects.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sample_profile_gis_project_2));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mListSize;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mListSize - 1){
            return VIEW_TYPE_ADD_GIS_PROJECT;
        }else {
            return VIEW_TYPE_GIS_PROJECT;
        }
    }
}
