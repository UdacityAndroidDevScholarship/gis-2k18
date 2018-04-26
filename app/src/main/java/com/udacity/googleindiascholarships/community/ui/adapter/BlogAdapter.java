package com.udacity.googleindiascholarships.community.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.challenges.ui.entities.Challenge;
import com.udacity.googleindiascholarships.community.ui.ExternalLinks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajat Kumar Gupta on 26-04-2018.
 */

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.LinkPreviewViewHolder> {

    public Context mContext;
    public List<ExternalLinks> mLinkItems;

    public BlogAdapter(Context context, List<ExternalLinks> mListItems) {
        mContext = context;
        this.mLinkItems = mListItems;
    }

    @Override
    public LinkPreviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_blog_and_resources, parent, false);
        return new LinkPreviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LinkPreviewViewHolder holder, int position) {
        ExternalLinks currentExternalLink = mLinkItems.get(position);
        holder.previewLinkText.setText(currentExternalLink.getLinkUrl());
        holder.previewLinkPostedBy.setText(currentExternalLink.getLinkPostedBy());
    }

    @Override
    public int getItemCount() {
        return mLinkItems.size();
    }

    public class LinkPreviewViewHolder extends RecyclerView.ViewHolder {
        public TextView previewLinkText;
        public TextView previewLinkPostedBy;

        public LinkPreviewViewHolder(View itemView) {
            super(itemView);
            previewLinkText = (TextView) itemView.findViewById(R.id.link_preview_title);
            previewLinkPostedBy = (TextView) itemView.findViewById(R.id.link_shared_by);

        }
    }
}


