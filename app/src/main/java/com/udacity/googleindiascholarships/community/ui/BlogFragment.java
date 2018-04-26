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
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {
    private List<ExternalLinks> blogLinks;
    private RecyclerView blogRecyclerView;

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
        loadData();

        BlogAdapter linkPreviewAdapter = new BlogAdapter(getContext(),blogLinks);
        blogRecyclerView.setAdapter(linkPreviewAdapter);

        return rootView;
    }

    private void loadData() {
        blogLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Rajat Kumar Gupta"));
        blogLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Vishal Sehgal"));
        blogLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Shubham Soni"));
        blogLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Anuj Jha"));
        blogLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Akshit Jain"));
        blogLinks.add(new ExternalLinks("https://github.com/UdacityAndroidDevScholarship/gis-2k18/issues/14","Rajat Kumar Gupta"));

    }
}
