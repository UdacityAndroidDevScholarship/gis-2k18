package com.udacity.googleindiascholarships.members.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by vinee_000 on 27-04-2018.
 */
public class ProfileGISStoriesAdapter extends RecyclerView.Adapter<ProfileGISStoriesAdapter.ProfileGISStoriesViewHolder>{
    public  ProfileGISStoriesAdapter() {
    }

    class  ProfileGISStoriesViewHolder extends RecyclerView.ViewHolder{
        public  ProfileGISStoriesViewHolder(View itemView) {
            super(itemView);

        }
    }
    @Override
    public ProfileGISStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_profile_gis_stories,parent,false);
        return new ProfileGISStoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( ProfileGISStoriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
