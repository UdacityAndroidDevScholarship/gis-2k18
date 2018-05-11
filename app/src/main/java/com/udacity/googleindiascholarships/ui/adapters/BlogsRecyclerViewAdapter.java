package com.udacity.googleindiascholarships.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.googleindiascholarships.R;

/**
 * Created by Sudhanshu on 10-05-2018.
 */

public class BlogsRecyclerViewAdapter extends RecyclerView.Adapter<BlogsRecyclerViewAdapter.BlogsViewHolder> {

    Context mContext;
    // Note: This is just a dummy variable
    String blogImageURL = "https://cdn-images-1.medium.com/max/1000/1*iFjXqXIxICNAwP9-6LHygQ.jpeg";
    String blogURL = "https://medium.com/@sudhanshuvohra8443/google-india-challenge-scholarship-nearing-the-end-of-a-great-journey-9216466d2614";
    private final static String BOOKMARKED = "Bookmarked";
    private final static String UNBOOKMARKED = "Un-bookmarked";

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
    public void onBindViewHolder(final BlogsViewHolder holder, int position) {
        Picasso.with(mContext).load(blogImageURL).into(holder.thumbnailImage);
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareArticle = new Intent(Intent.ACTION_SEND);
                shareArticle.setType("text/plain");
                shareArticle.putExtra(Intent.EXTRA_TITLE, "Hey I just read this awesome article. Have a look.");
                shareArticle.putExtra(Intent.EXTRA_TEXT, blogURL);
                mContext.startActivity(Intent.createChooser(shareArticle, "Share Article"));
            }
        });
        holder.openInBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(blogURL));
                mContext.startActivity(browserIntent);
            }
        });
        holder.bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.bookmark.getTag().equals(UNBOOKMARKED)){
                    holder.bookmark.setTag(BOOKMARKED);
                    holder.bookmark.setImageResource(R.drawable.ic_bookmark_checked);
                    Toast.makeText(mContext, "Blog " + BOOKMARKED, Toast.LENGTH_SHORT).show();
                }
                else{
                    holder.bookmark.setTag(UNBOOKMARKED);
                    holder.bookmark.setImageResource(R.drawable.ic_bookmark);
                    Toast.makeText(mContext, "Blog " + UNBOOKMARKED, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class BlogsViewHolder extends RecyclerView.ViewHolder {

        ImageButton bookmark, share,openInBrowser;
        ImageView thumbnailImage;

        public BlogsViewHolder(View itemView) {
            super(itemView);

            bookmark = (ImageButton) itemView.findViewById(R.id.ib_bookmark_blog);
            share = (ImageButton) itemView.findViewById(R.id.ib_share_blog);
            openInBrowser = (ImageButton) itemView.findViewById(R.id.ib_open_blog);
            thumbnailImage = (ImageView) itemView.findViewById(R.id.img_view_blog_thumbnail);
            bookmark.setTag(UNBOOKMARKED);
        }
    }
}
