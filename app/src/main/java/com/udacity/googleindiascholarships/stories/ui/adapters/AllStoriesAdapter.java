package com.udacity.googleindiascholarships.stories.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by vinee_000 on 20-04-2018.
 */

public class AllStoriesAdapter extends RecyclerView.Adapter<AllStoriesAdapter.AllStoriesViewHolder>{
    public AllStoriesAdapter() {
    }

    class AllStoriesViewHolder extends RecyclerView.ViewHolder{
        public AllStoriesViewHolder(View itemView) {
            super(itemView);

        }
    }
    @Override
    public AllStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_all_stories,parent,false);
        return new AllStoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllStoriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
