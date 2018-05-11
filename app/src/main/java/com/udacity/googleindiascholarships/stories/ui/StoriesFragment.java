package com.udacity.googleindiascholarships.stories.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.udacity.googleindiascholarships.community.ui.entities.ExternalLinks;
import com.udacity.googleindiascholarships.stories.ui.adapters.AllStoriesAdapter;
import com.udacity.googleindiascholarships.stories.ui.adapters.FeaturedStoriesAdapter;
import com.udacity.googleindiascholarships.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoriesFragment extends android.support.v4.app.Fragment{


    RecyclerView rvFeaturedStories,rvAllStories;
    LinearLayoutManager llmFeaturedStories,llmAllStories;
    FeaturedStoriesAdapter featuredStoriesAdapter;
    AllStoriesAdapter allStoriesAdapter;
    private int numberOfFeaturedStories = 10;
    private List<ExternalLinks> featuredStoriesLinks;
    private List<ExternalLinks> allStoriesLinks;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View customView =  inflater.inflate(R.layout.fragment_stories,container,false);

        rvFeaturedStories = (RecyclerView) customView.findViewById(R.id.rv_featured_stories);
        rvAllStories = (RecyclerView) customView.findViewById(R.id.rv_all_stories);


        llmFeaturedStories = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvFeaturedStories.setLayoutManager(llmFeaturedStories);
        allStoriesLinks = new ArrayList<ExternalLinks>();
        featuredStoriesLinks = new ArrayList<ExternalLinks>();
        readFeaturedStoriesFromFirebase();
        readAllStoriesFromFirebase();

        featuredStoriesAdapter = new FeaturedStoriesAdapter(getContext(),allStoriesLinks,numberOfFeaturedStories);
        rvFeaturedStories.setAdapter(featuredStoriesAdapter);

        llmAllStories = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvAllStories.setLayoutManager(llmAllStories);

        allStoriesAdapter = new AllStoriesAdapter(getContext(),allStoriesLinks);
        rvAllStories.setAdapter(allStoriesAdapter);

        return customView;
    }

    private void readFeaturedStoriesFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
<<<<<<< HEAD
        DatabaseReference mExternalResourcesRef = database.getReference("stories");
=======
        DatabaseReference mExternalResourcesRef = database.getReference("external_resources")
<<<<<<< HEAD
                .child("stories");
=======
                .child("blogs");
>>>>>>> upstream/master
>>>>>>> upstream/master
        mExternalResourcesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                featuredStoriesLinks.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ExternalLinks currentBlog = postSnapshot.getValue(ExternalLinks.class);
                    featuredStoriesLinks.add(currentBlog);
                }
                Log.i("TAG", "onDataChange: Hello Testing");
                featuredStoriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void readAllStoriesFromFirebase() {
<<<<<<< HEAD
        final FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        DatabaseReference mExternalResourcesRef = database.getReference("stories");
=======
        FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        DatabaseReference mExternalResourcesRef = database.getReference("external_resources")
<<<<<<< HEAD
                .child("stories");
=======
                .child("blogs");
>>>>>>> upstream/master
>>>>>>> upstream/master
        mExternalResourcesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                allStoriesLinks.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ExternalLinks currentBlog = postSnapshot.getValue(ExternalLinks.class);
                    allStoriesLinks.add(currentBlog);
                }
                Log.i("TAG", "onDataChange: Hello Testing");
                allStoriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("TAG", "onDataChange: "+databaseError.getMessage());

                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Stories");
    }
}
