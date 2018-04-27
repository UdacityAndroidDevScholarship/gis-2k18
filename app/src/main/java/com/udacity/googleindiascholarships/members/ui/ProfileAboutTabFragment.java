package com.udacity.googleindiascholarships.members.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.googleindiascholarships.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileAboutTabFragment extends Fragment {


    public ProfileAboutTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_about_tab, container, false);
    }

}
