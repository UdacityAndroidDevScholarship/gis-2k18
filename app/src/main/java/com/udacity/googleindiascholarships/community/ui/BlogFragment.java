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
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {
    private List<ExternalLinks> blogLinks;
    private RecyclerView blogRecyclerView;
    private BlogAdapter blogAdapter;
    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_blog, container, false);
        blogRecyclerView = rootView.findViewById(R.id.blogs_recyclerView);
        blogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        blogLinks = new ArrayList<ExternalLinks>();
        readFromFirebase();
        blogAdapter = new BlogAdapter(getContext(),blogLinks);
        blogRecyclerView.setAdapter(blogAdapter);

        return rootView;
    }

    private void readFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        DatabaseReference mExternalResourcesRef = database.getReference("external_resources").child("blogs");
        mExternalResourcesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                blogLinks.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ExternalLinks currentBlog = postSnapshot.getValue(ExternalLinks.class);
                    blogLinks.add(currentBlog);
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
