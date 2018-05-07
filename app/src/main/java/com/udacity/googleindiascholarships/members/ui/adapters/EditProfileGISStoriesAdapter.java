package com.udacity.googleindiascholarships.members.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by HP on 07-05-2018.
 */

public class EditProfileGISStoriesAdapter extends RecyclerView.Adapter<EditProfileGISStoriesAdapter.EditProfileGISStoriesViewHolder> {

    private static int mListSize = 10;
    private static final int VIEW_TYPE_ADD_GIS_STORY = 0;
    private static final int VIEW_TYPE_GIS_STORY = 1;

    public  EditProfileGISStoriesAdapter() {
    }

    class  EditProfileGISStoriesViewHolder extends RecyclerView.ViewHolder{
        public  EditProfileGISStoriesViewHolder(View itemView) {
            super(itemView);

        }
    }
    @Override
    public EditProfileGISStoriesAdapter.EditProfileGISStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.list_item_profile_gis_stories;
        switch (viewType){
            case VIEW_TYPE_ADD_GIS_STORY:
                layoutId = R.layout.list_item_add_gis_story;
                break;
            case VIEW_TYPE_GIS_STORY:
                layoutId = R.layout.list_item_profile_gis_stories;
                break;
        }
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        return new EditProfileGISStoriesAdapter.EditProfileGISStoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EditProfileGISStoriesAdapter.EditProfileGISStoriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mListSize;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mListSize - 1){
            return VIEW_TYPE_ADD_GIS_STORY;
        }else {
            return VIEW_TYPE_GIS_STORY;
        }
    }
}
