package com.udacity.googleindiascholarships.stories.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leocardz.link.preview.library.LinkPreviewCallback;
import com.leocardz.link.preview.library.SourceContent;
import com.leocardz.link.preview.library.TextCrawler;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.community.ui.entities.ExternalLinks;

import java.util.List;
import java.util.Random;

public class FeaturedStoriesAdapter extends RecyclerView.Adapter<FeaturedStoriesAdapter.FeaturedStoriesViewHolder> {

    private Context mContext;
    private List<ExternalLinks> mLinkItems;
    private int numberOfStories;

    public FeaturedStoriesAdapter() {
    }

    public FeaturedStoriesAdapter(Context context, List<ExternalLinks> mListItems, int numberOfFeaturedStories) {
        this.mContext = context;
        this.mLinkItems = mListItems;
        this.numberOfStories = numberOfFeaturedStories;
    }


    @Override
    public FeaturedStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_featured_stories, parent, false);
        return new FeaturedStoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeaturedStoriesViewHolder holder, int position) {

        final ExternalLinks currentExternalLink = mLinkItems.get(position);

        holder.featuredStoryProfileNameText.setText(currentExternalLink.getLinkPostedBy());
        holder.featuredStoryTitleText.setText(currentExternalLink.getLinkUrl());
        holder.featuredStoryTitleText.setText(currentExternalLink.getLinkDescription());
        holder.featuredStoryTitleText.setText(currentExternalLink.getLinkDescription());
        holder.featuredStoryReadText.setOnClickListener(new View.OnClickListener() {
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

    public class FeaturedStoriesViewHolder extends RecyclerView.ViewHolder {
        private TextView featuredStoryProfileNameText;
        private TextView featuredStoryTitleText;
        private TextView featuredStoryReadText;

        public FeaturedStoriesViewHolder(View itemView) {
            super(itemView);
            featuredStoryProfileNameText = itemView.findViewById(R.id.tv_featured_story_profile_name);
            featuredStoryTitleText = itemView.findViewById(R.id.tv_featured_story_title);
            featuredStoryReadText = itemView.findViewById(R.id.iv_featured_story_date);
        }
    }
}
