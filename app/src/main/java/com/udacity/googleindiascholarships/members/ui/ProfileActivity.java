package com.udacity.googleindiascholarships.members.ui;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.members.ui.adapters.ProfileViewPagerAdapter;
import com.udacity.googleindiascholarships.utils.Constants;

public class ProfileActivity extends AppCompatActivity {

    ViewPager vpProfile;
    TabLayout tabsProfile;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutProfile;
    FloatingActionButton editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vpProfile = (ViewPager) findViewById(R.id.vpProfile);
        tabsProfile = (TabLayout)findViewById(R.id.tabsProfile);
        editProfile = (FloatingActionButton) findViewById(R.id.fab_edit_profile);

        if(getIntent() != null){
            if(getIntent().hasExtra(Constants.PROFILE_ACTIVITY_EDITABLE_KEY)){
                boolean editable = getIntent().getBooleanExtra(Constants.PROFILE_ACTIVITY_EDITABLE_KEY, false);
                if(editable){
                    editProfile.setVisibility(View.VISIBLE);
                }else {
                    editProfile.setVisibility(View.GONE);
                }
            }else{
                // Not editable as no extra constant has been passed
                editProfile.setVisibility(View.GONE);
            }
        }

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("Phase 1");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbarLayoutProfile = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_profile);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editProfileIntent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(editProfileIntent);
            }
        });
        vpProfile.setOffscreenPageLimit(3);
        setupViewPager();
        tabsProfile.setupWithViewPager(vpProfile);

    }
    private void setupViewPager() {
        ProfileViewPagerAdapter adapter = new ProfileViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileAboutTabFragment(), "About");
        adapter.addFragment(new ProfileGISTabFragment(), "GIS");
        adapter.addFragment(new ProfileProjectsTabFragment(), "Projects");
        vpProfile.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

