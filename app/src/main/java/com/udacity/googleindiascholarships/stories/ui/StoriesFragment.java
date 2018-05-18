package com.udacity.googleindiascholarships.stories.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
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

        if(checkInternetConnectivity()){
            readFeaturedStoriesFromFirebase();
            readAllStoriesFromFirebase();
            featuredStoriesAdapter = new FeaturedStoriesAdapter(getContext(),allStoriesLinks,numberOfFeaturedStories);
            rvFeaturedStories.setAdapter(featuredStoriesAdapter);

            llmAllStories = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            rvAllStories.setLayoutManager(llmAllStories);

            allStoriesAdapter = new AllStoriesAdapter(getContext(),allStoriesLinks);
            rvAllStories.setAdapter(allStoriesAdapter);
        }else{
            Toast.makeText(getContext(),"No internet connection",Toast.LENGTH_LONG).show();

        }





        return customView;
    }

    private void readFeaturedStoriesFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        DatabaseReference mExternalResourcesRef = database.getReference("stories");
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
        final FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
        DatabaseReference mExternalResourcesRef = database.getReference("stories");
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

    public boolean checkInternetConnectivity(){
        //Check internet connection//
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network//
        NetworkInfo netInformation = connectivityManager.getActiveNetworkInfo();
        // If there is a network connection, then fetch data//
        if(netInformation!=null && netInformation.isConnected()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
