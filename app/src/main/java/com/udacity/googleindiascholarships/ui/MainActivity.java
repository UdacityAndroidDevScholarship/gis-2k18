package com.udacity.googleindiascholarships.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.amulyakhare.textdrawable.TextDrawable;
import com.udacity.googleindiascholarships.ChallengesFragment;
import com.udacity.googleindiascholarships.CommunityFragment;
import com.udacity.googleindiascholarships.MembersFragment;
import com.udacity.googleindiascholarships.ProjectsFragment;
import com.udacity.googleindiascholarships.QuizzesFragment;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.SettingsFragment;
import com.udacity.googleindiascholarships.StoriesFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner spCourses;
    ImageView ivNavHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ivNavHeader = navigationView.getHeaderView(0).findViewById(R.id.ivNavHeader);
        spCourses = navigationView.getHeaderView(0).findViewById(R.id.spCourses);
        spCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                int textColor = Color.parseColor("#00aeef");
                int backgroundColor = Color.parseColor("#ffffff");

                if(position == 0){

                    TextDrawable drawable = TextDrawable
                            .builder()
                            .beginConfig()
                            .bold()
                            .textColor(textColor)
                            .toUpperCase()
                            .withBorder(5)
                            .endConfig().buildRound("AB",backgroundColor);
                    ivNavHeader.setImageDrawable(drawable);


                }else if(position == 1){

                    TextDrawable drawable = TextDrawable
                            .builder()
                            .beginConfig()
                            .bold()
                            .textColor(textColor)
                            .toUpperCase()
                            .withBorder(5)
                            .endConfig().buildRound("AI",backgroundColor);
                    ivNavHeader.setImageDrawable(drawable);


                }else if(position == 2){

                    TextDrawable drawable = TextDrawable
                            .builder()
                            .beginConfig()
                            .bold()
                            .textColor(textColor)
                            .toUpperCase()
                            .withBorder(5)
                            .endConfig().buildRound("WB",backgroundColor);
                    ivNavHeader.setImageDrawable(drawable);


                }else if(position == 3){
                    TextDrawable drawable = TextDrawable
                            .builder()
                            .beginConfig()
                            .bold()
                            .textColor(textColor)
                            .toUpperCase()
                            .withBorder(5)
                            .endConfig().buildRound("WI",backgroundColor);
                    ivNavHeader.setImageDrawable(drawable);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);
        return true;
    }

    public void displaySelectedScreen(int id){

        android.support.v4.app.Fragment fragment = null ;

        switch (id){

            case R.id.nav_members:
                fragment = new MembersFragment();
                break;
            case R.id.nav_gis_stories:
                fragment = new StoriesFragment();
                break;
            case R.id.nav_projects:
                fragment = new ProjectsFragment();
                break;
            case R.id.nav_challenges:
                fragment = new ChallengesFragment();
                break;
            case R.id.nav_quizzes:
                fragment = new QuizzesFragment();
                break;
            case R.id.nav_community:
                fragment = new CommunityFragment();
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
        }

        if(fragment != null){
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment).addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
