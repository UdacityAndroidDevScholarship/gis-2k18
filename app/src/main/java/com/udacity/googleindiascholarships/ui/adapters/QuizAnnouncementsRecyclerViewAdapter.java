package com.udacity.googleindiascholarships.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.udacity.googleindiascholarships.R;

/**
 * Created by Sudhanshu on 12-05-2018.
 */

public class QuizAnnouncementsRecyclerViewAdapter extends RecyclerView.Adapter<QuizAnnouncementsRecyclerViewAdapter.QuizAnnouncementsViewHolder> {

    Context mContext;
    // Dummy variable till quiz url's are not bound to the quiz data
    private final static String quizUrl = "https://goo.gl/forms/ADSwKBocfqVFPxiV2";
    private final static String BOOKMARKED = "Bookmarked";
    private final static String UNBOOKMARKED = "Un-bookmarked";

    public QuizAnnouncementsRecyclerViewAdapter(Context context){
        mContext = context;
    }

    @Override
    public QuizAnnouncementsRecyclerViewAdapter.QuizAnnouncementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_quiz_announcement, null);
        QuizAnnouncementsViewHolder viewHolder = new QuizAnnouncementsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final QuizAnnouncementsRecyclerViewAdapter.QuizAnnouncementsViewHolder holder, int position) {
        holder.shareQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, quizUrl);
                mContext.startActivity(Intent.createChooser(shareIntent, "Share the quiz"));
            }
        });
        holder.openInBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(quizUrl));
                mContext.startActivity(openInBrowser);
            }
        });
        holder.bookmarkQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.bookmarkQuiz.getTag().equals(UNBOOKMARKED)){
                    holder.bookmarkQuiz.setTag(BOOKMARKED);
                    holder.bookmarkQuiz.setImageResource(R.drawable.ic_bookmark_checked);
                    Toast.makeText(mContext, "Quiz " + BOOKMARKED, Toast.LENGTH_SHORT).show();
                }
                else{
                    holder.bookmarkQuiz.setTag(UNBOOKMARKED);
                    holder.bookmarkQuiz.setImageResource(R.drawable.ic_bookmark);
                    Toast.makeText(mContext, "Quiz " + UNBOOKMARKED, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class QuizAnnouncementsViewHolder extends RecyclerView.ViewHolder{

        ImageButton shareQuiz, openInBrowser, bookmarkQuiz;

        public QuizAnnouncementsViewHolder(View itemView) {
            super(itemView);

            shareQuiz = itemView.findViewById(R.id.ib_share_quiz);
            openInBrowser = itemView.findViewById(R.id.ib_open_quiz);
            bookmarkQuiz = itemView.findViewById(R.id.ib_bookmark_quiz);
            bookmarkQuiz.setTag(UNBOOKMARKED);
        }
    }
}
