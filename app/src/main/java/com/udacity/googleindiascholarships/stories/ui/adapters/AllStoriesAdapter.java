package com.udacity.googleindiascholarships.stories.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.community.ui.entities.ExternalLinks;

import java.util.List;

public class AllStoriesAdapter extends RecyclerView.Adapter<AllStoriesAdapter.AllStoriesViewHolder>{
    public Context mContext;
    public List<ExternalLinks> mLinkItems;

    public AllStoriesAdapter() {
    }
    public AllStoriesAdapter(Context context, List<ExternalLinks> mListItems) {
        mContext = context;
        this.mLinkItems = mListItems;
    }

    @Override
    public AllStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_all_stories,parent,false);
        return new AllStoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllStoriesViewHolder holder, int position) {
        final ExternalLinks currentExternalLink = mLinkItems.get(position);
        holder.storyProfileNameText.setText(currentExternalLink.getLinkPostedBy());
<<<<<<< HEAD
<<<<<<< HEAD
        holder.storyTitleText.setText(currentExternalLink.getLinkUrl());
=======
        holder.storyTitleText.setText(currentExternalLink.getLinkDescription());
>>>>>>> upstream/master
=======
        holder.storyTitleText.setText(currentExternalLink.getLinkDescription());
>>>>>>> adding_link_preview
        holder.storyReadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentExternalLink.getLinkUrl()));
                mContext.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLinkItems.size();
    }

    public class AllStoriesViewHolder extends RecyclerView.ViewHolder{
        private TextView storyProfileNameText;
        private TextView storyTitleText;
        private TextView storyReadText;

        public AllStoriesViewHolder(View itemView) {
            super(itemView);
            storyProfileNameText = itemView.findViewById(R.id.tv_all_story_profile_name);
            storyTitleText = itemView.findViewById(R.id.tv_all_story_title);
            storyReadText = itemView.findViewById(R.id.tv_read_txt);
        }
    }

}
