package com.udacity.googleindiascholarships.members.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.udacity.googleindiascholarships.R;

/**
 * Created by vinee_000 on 27-04-2018.
 */

public class ProfileGISProjectsAdapter extends RecyclerView.Adapter<ProfileGISProjectsAdapter.ProfileGISProjectsViewHolder>{

    Context mContext;

    public ProfileGISProjectsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    class  ProfileGISProjectsViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileGISProjects;
        public  ProfileGISProjectsViewHolder(View itemView) {
            super(itemView);
            ivProfileGISProjects = (ImageView) itemView.findViewById(R.id.iv_profile_gis_project_image);

        }
    }
    @Override
    public ProfileGISProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_profile_gis_projects,parent,false);
        return new ProfileGISProjectsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( ProfileGISProjectsViewHolder holder, int position) {
        if(position%2 == 0){
            holder.ivProfileGISProjects.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sample_profile_gis_project_1));
        }else{
            holder.ivProfileGISProjects.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sample_profile_gis_project_2));
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }


}