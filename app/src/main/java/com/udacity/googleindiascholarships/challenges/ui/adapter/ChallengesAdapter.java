package com.udacity.googleindiascholarships.challenges.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.challenges.ui.entities.Challenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengesAdapter extends RecyclerView.Adapter<ChallengesAdapter.ChallengesCardViewHolder> {

    public Context mContext;
    public List<Challenge> mListItems;
    public ArrayList<Challenge> mProjectListItems;

    public ChallengesAdapter(Context context, List<Challenge> mListItems) {
        mContext = context;
        this.mListItems = mListItems;
    }

    @Override
    public ChallengesAdapter.ChallengesCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_challenges, parent, false);
        return new ChallengesAdapter.ChallengesCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChallengesAdapter.ChallengesCardViewHolder holder, int position) {
        final Challenge listItem = mListItems.get(position);
        holder.challengeAuthorNameTextView.setText(listItem.getAuthorNameChallenge());
        // holder.projectImageView.setImageResource(listItem.getPlaceHolderImage());
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public class ChallengesCardViewHolder extends RecyclerView.ViewHolder {

        public TextView challengeAuthorNameTextView;

        public ChallengesCardViewHolder(View itemView) {
            super(itemView);
            challengeAuthorNameTextView = (TextView) itemView.findViewById(R.id.tv_author_name_challenges);

        }
    }
}


