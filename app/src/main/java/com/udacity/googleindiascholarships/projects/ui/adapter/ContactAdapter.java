package com.udacity.googleindiascholarships.projects.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.projects.entities.ContactModerator;
import com.udacity.googleindiascholarships.projects.entities.Project;
import com.udacity.googleindiascholarships.projects.ui.ProjectDetails;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactCardViewHolder> {

    public Context mContext;
    public List<ContactModerator> mListItems;
    public ArrayList<ContactModerator> mProjectListItems;
    public String profileLink;
    public String nameContact;
    public String titleContact;
    public String linkedInContact;
    public String googlePlusContact;
    public String githubContact;


    public ContactAdapter(Context context, List<ContactModerator> mListItems) {
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
        final ContactModerator listItem = mListItems.get(position);

       // holder.contactImageView.setImageResource(listItem.getPlaceHolderImage());
        Picasso.with(mContext).load(listItem.getMod_profile()).into(holder.contactImageView);

    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public class ContactCardViewHolder extends RecyclerView.ViewHolder {

      CircleImageView contactImageView;


        public ContactCardViewHolder(final View itemView) {
            super(itemView);
            contactImageView = itemView.findViewById(R.id.civ_contactImageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                     int position = getAdapterPosition();


                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    View itemView = LayoutInflater.from(mContext).inflate(R.layout.custom_contact,null);
                    CircleImageView profileImage = itemView.findViewById(R.id.contact_profile);
                    TextView contact_name = itemView.findViewById(R.id.contact_name);
                    TextView contact_title = itemView.findViewById(R.id.contact_title);
                    CircleImageView linkedInButton = itemView.findViewById(R.id.contact_linkedIn);
                    CircleImageView googlePlus = itemView.findViewById(R.id.contact_googleplus);
                    CircleImageView githubLink = itemView.findViewById(R.id.contact_github);
                    TextView emailGis = itemView.findViewById(R.id.gisMailId);

                    Picasso.with(mContext).load(mListItems.get(position).getMod_profile()).into(profileImage);
                    contact_name.setText(mListItems.get(position).getMod_name());
                    contact_title.setText(mListItems.get(position).getMod_title());

                    linkedInButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Uri githHubUri = Uri.parse(mListItems.get(getAdapterPosition()).getMod_linkedin());
                            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, githHubUri);
                            mContext.startActivity(websiteIntent);

                        }
                    });

                    googlePlus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String address [] = {mListItems.get(getAdapterPosition()).getMod_email()};
                            Intent intent = new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                            intent.putExtra(Intent.EXTRA_EMAIL, address);
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Query :");
                            if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                                mContext.startActivity(intent);
                            }

                        }
                    });
                    githubLink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Uri githHubUri = Uri.parse(mListItems.get(getAdapterPosition()).getMod_github());
                            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, githHubUri);
                            mContext.startActivity(websiteIntent);

                        }
                    });

                    emailGis.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String address [] = {"gisudacity2k18@gmail.com"};
                            Intent intent = new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                            intent.putExtra(Intent.EXTRA_EMAIL, address);
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Query :");
                            if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                                mContext.startActivity(intent);
                            }

                        }
                    });

                    builder.setView(itemView);

                    AlertDialog dialog = builder.create();
                    dialog.setTitle("About");
                    dialog.show();
                }
            });




        }
    }

}
