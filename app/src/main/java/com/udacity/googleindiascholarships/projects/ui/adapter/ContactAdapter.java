package com.udacity.googleindiascholarships.projects.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.projects.entities.Project;
import com.udacity.googleindiascholarships.projects.ui.ProjectDetails;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactCardViewHolder> {

    public Context mContext;
    public List<Project> mListItems;
    public ArrayList<Project> mProjectListItems;

    public ContactAdapter(Context context, List<Project> mListItems) {
        mContext = context;
        this.mListItems = mListItems;
    }

    @Override
    public ContactCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_contact, parent, false);
        return new ContactCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ContactCardViewHolder holder, int position) {
        final Project listItem = mListItems.get(position);

        holder.contactImageView.setImageResource(listItem.getPlaceHolderImage());

    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public class ContactCardViewHolder extends RecyclerView.ViewHolder {

      CircleImageView contactImageView;


        public ContactCardViewHolder(View itemView) {
            super(itemView);

            contactImageView = itemView.findViewById(R.id.civ_contactImageView);




        }
    }

}
