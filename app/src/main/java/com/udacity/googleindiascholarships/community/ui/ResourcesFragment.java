package com.udacity.googleindiascholarships.community.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.community.ui.adapter.BlogAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResourcesFragment extends Fragment {


    private RecyclerView resourcesRecyclerView;
    private ArrayList<ExternalLinks> resourcesLinks;

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
        loadData();
        BlogAdapter linkPreviewAdapter = new BlogAdapter(getContext(), resourcesLinks);
        resourcesRecyclerView.setAdapter(linkPreviewAdapter);
        return rootView;
    }
    private void loadData() {
        resourcesLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Rajat Kumar Gupta"));
        resourcesLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Vishal Sehgal"));
        resourcesLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Shubham Soni"));
        resourcesLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Anuj Jha"));
        resourcesLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Akshit Jain"));
        resourcesLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Rajat Kumar Gupta"));

    }

}
