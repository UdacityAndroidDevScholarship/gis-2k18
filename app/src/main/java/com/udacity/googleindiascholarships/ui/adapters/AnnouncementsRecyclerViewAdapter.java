package com.udacity.googleindiascholarships.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by Sudhanshu on 10-05-2018.
 */

public class AnnouncementsRecyclerViewAdapter extends RecyclerView.Adapter<AnnouncementsRecyclerViewAdapter.AnnouncementsViewHolder>{

    Context mContext;

    public AnnouncementsRecyclerViewAdapter(Context context){
        mContext = context;
    }


    @Override
    public AnnouncementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_announcements, null);
        AnnouncementsViewHolder viewHolder = new AnnouncementsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnnouncementsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class AnnouncementsViewHolder extends RecyclerView.ViewHolder {
        public AnnouncementsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
