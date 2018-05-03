package com.udacity.googleindiascholarships.community.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.community.ui.adapter.BlogAdapter;
import com.udacity.googleindiascholarships.community.ui.entities.ExternalLinks;
import com.udacity.googleindiascholarships.utils.Constants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResourcesFragment extends Fragment {


    private RecyclerView resourcesRecyclerView;
    private ArrayList<ExternalLinks> resourcesLinks;
    private BlogAdapter blogAdapter;

    public ResourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_blog, container, false);
        resourcesRecyclerView = rootView.findViewById(R.id.blogs_recyclerView);
        resourcesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        resourcesLinks = new ArrayList<ExternalLinks>();
        readFromFirebase();
        blogAdapter = new BlogAdapter(getContext(),resourcesLinks);
        resourcesRecyclerView.setAdapter(blogAdapter);
        return rootView;
    }

    private void readFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        DatabaseReference mExternalResourcesRef = database.getReference("external_resources").child("resources");
        mExternalResourcesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                resourcesLinks.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ExternalLinks currentResource = postSnapshot.getValue(ExternalLinks.class);
                    resourcesLinks.add(currentResource);
                }

                blogAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
