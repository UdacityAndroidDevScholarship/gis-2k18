package com.udacity.googleindiascholarships.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
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
        Picasso.with(mContext).load(R.drawable.akshit).
                into(holder.announcementsThumbnail);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class AnnouncementsViewHolder extends RecyclerView.ViewHolder {
        ImageView announcementsThumbnail;
        public AnnouncementsViewHolder(View itemView) {
            super(itemView);
            announcementsThumbnail = itemView.findViewById(R.id.img_view_announcement_thumbnail);
        }
    }
}
