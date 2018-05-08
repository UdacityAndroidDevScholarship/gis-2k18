package com.udacity.googleindiascholarships.challenges.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.challenges.entities.Challenge;
import com.udacity.googleindiascholarships.challenges.ui.ChallengesDetails;

import java.util.ArrayList;
import java.util.List;

public class ChallengesListAdapter extends RecyclerView.Adapter<ChallengesListAdapter.ChallengesCardViewHolder> {

    public Context mContext;
    public List<Challenge> mListItems;
    public ArrayList<Challenge> mProjectListItems;

    public ChallengesListAdapter(Context context, List<Challenge> mListItems) {
        mContext = context;
        this.mListItems = mListItems;
    }

    @Override
    public ChallengesListAdapter.ChallengesCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_challenge_names, parent, false);
        return new ChallengesListAdapter.ChallengesCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChallengesListAdapter.ChallengesCardViewHolder holder, int position) {
        final Challenge listItem = mListItems.get(position);
        holder.challengeNameTextView.setText(listItem.getChallenge_name());
        // holder.projectImageView.setImageResource(listItem.getPlaceHolderImage());
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public class ChallengesCardViewHolder extends RecyclerView.ViewHolder {

        public TextView challengeNameTextView;

        public ChallengesCardViewHolder(View itemView) {
            super(itemView);
            challengeNameTextView = (TextView) itemView.findViewById(R.id.tv_challenge_name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, ChallengesDetails.class);
                    intent.putExtra("child",mListItems.get(position).getChallenge_name());
                    context.startActivity(intent);
                }
            });

        }
    }
}




