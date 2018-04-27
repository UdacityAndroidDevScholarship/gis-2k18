package com.udacity.googleindiascholarships.members.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.members.ui.ProfileActivity;

/**
 * Created by vinee_000 on 27-04-2018.
 */

public class Phase2MembersAdapter extends RecyclerView.Adapter<Phase2MembersAdapter.Phase2MembersViewHolder>{

    Context mContext;

    public Phase2MembersAdapter(Context mContext) {
        this.mContext = mContext;
    }

    class  Phase2MembersViewHolder extends RecyclerView.ViewHolder{

        CardView cvPhase2Member;
        TextView tvPhase2MemberSeeMore;

        Phase2MembersViewHolder(View itemView) {
            super(itemView);
            cvPhase2Member = (CardView) itemView.findViewById(R.id.cv_phase2_member);
            tvPhase2MemberSeeMore = (TextView) itemView.findViewById(R.id.tv_phase2_member_see_more);

        }

    }
    @Override
    public Phase2MembersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_phase2_member,parent,false);
        return new Phase2MembersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( Phase2MembersViewHolder holder, int position) {
        holder.tvPhase2MemberSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProfileActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }


}