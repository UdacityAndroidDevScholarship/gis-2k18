package com.udacity.googleindiascholarships.members.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.members.ui.ProfileActivity;

/**
 * Created by vinee_000 on 27-04-2018.
 */

public class Phase1MembersAdapter extends RecyclerView.Adapter<Phase1MembersAdapter.Phase1MembersViewHolder>{

    Context mContext;

    public Phase1MembersAdapter(Context mContext) {
        this.mContext = mContext;
    }

    class  Phase1MembersViewHolder extends RecyclerView.ViewHolder{

        CardView cvPhase1Member;
        TextView tvPhase1MemberSeeMore;
        Phase1MembersViewHolder(View itemView) {
            super(itemView);
            cvPhase1Member = (CardView) itemView.findViewById(R.id.cv_phase1_member);
            tvPhase1MemberSeeMore = (TextView) itemView.findViewById(R.id.tv_phase1_member_see_more);

        }
    }
    @Override
    public Phase1MembersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_phase1_member,parent,false);
        return new Phase1MembersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( Phase1MembersViewHolder holder, int position) {
        holder.tvPhase1MemberSeeMore.setOnClickListener(new View.OnClickListener() {
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