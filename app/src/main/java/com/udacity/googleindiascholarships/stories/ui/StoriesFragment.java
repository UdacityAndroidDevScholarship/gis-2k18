package com.udacity.googleindiascholarships.stories.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * Created by jha.anuj.2108 on 13-04-2018.
 */

public class StoriesFragment extends android.support.v4.app.Fragment{


    RecyclerView rvFeaturedStories,rvAllStories;
    LinearLayoutManager llmFeaturedStories,llmAllStories;
    FeaturedStoriesAdapter featuredStoriesAdapter;
    AllStoriesAdapter allStoriesAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View customView =  inflater.inflate(R.layout.fragment_stories,container,false);

        rvFeaturedStories = (RecyclerView) customView.findViewById(R.id.rv_featured_stories);
        rvAllStories = (RecyclerView) customView.findViewById(R.id.rv_all_stories);


        llmFeaturedStories = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvFeaturedStories.setLayoutManager(llmFeaturedStories);

        featuredStoriesAdapter = new FeaturedStoriesAdapter();
        rvFeaturedStories.setAdapter(featuredStoriesAdapter);


        llmAllStories = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvAllStories.setLayoutManager(llmAllStories);

        allStoriesAdapter = new AllStoriesAdapter();
        rvAllStories.setAdapter(allStoriesAdapter);

        return customView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Stories");
    }
}
