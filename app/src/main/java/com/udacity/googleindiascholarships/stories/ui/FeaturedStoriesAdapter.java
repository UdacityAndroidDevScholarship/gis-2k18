package com.udacity.googleindiascholarships.stories.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by vinee_000 on 19-04-2018.
 */

public class FeaturedStoriesAdapter extends RecyclerView.Adapter<FeaturedStoriesAdapter.FeaturedStoriesViewHolder>{
    public FeaturedStoriesAdapter() {
    }

    class FeaturedStoriesViewHolder extends RecyclerView.ViewHolder{
        public FeaturedStoriesViewHolder(View itemView) {
            super(itemView);

        }
    }
    @Override
    public FeaturedStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_featured_stories,parent,false);
        return new FeaturedStoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeaturedStoriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
