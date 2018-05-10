package com.udacity.googleindiascholarships.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by Sudhanshu on 10-05-2018.
 */

public class BlogsRecyclerViewAdapter extends RecyclerView.Adapter<BlogsRecyclerViewAdapter.BlogsViewHolder> {

    Context mContext;

    public BlogsRecyclerViewAdapter(Context context){
        mContext = context;
    }


    @Override
    public BlogsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_blogs, null);
        BlogsViewHolder viewHolder = new BlogsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BlogsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class BlogsViewHolder extends RecyclerView.ViewHolder {
        public BlogsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
