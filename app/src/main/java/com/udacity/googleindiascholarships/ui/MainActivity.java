package com.udacity.googleindiascholarships.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.amulyakhare.textdrawable.TextDrawable;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.challenges.ui.ChallengesFragment;
import com.udacity.googleindiascholarships.community.ui.CommunityFragment;
import com.udacity.googleindiascholarships.currentuser.ui.UserDetailsActivity;
import com.udacity.googleindiascholarships.members.ui.MembersFragment;
import com.udacity.googleindiascholarships.projects.ui.ProjectsFragment;
import com.udacity.googleindiascholarships.quizzes.ui.QuizzesFragment;
import com.udacity.googleindiascholarships.stories.ui.StoriesFragment;
import com.udacity.googleindiascholarships.ui.adapters.AnnouncementsRecyclerViewAdapter;
import com.udacity.googleindiascholarships.ui.adapters.BlogsRecyclerViewAdapter;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner spCourses;
    ImageView ivNavHeader;
    ArrayAdapter<CharSequence> courseSpinnerAdapter;
    AnnouncementsRecyclerViewAdapter announcementsRecyclerViewAdapter;
    BlogsRecyclerViewAdapter blogsRecyclerViewAdapter;
    RecyclerView blogsRecyclerView, announcementsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ivNavHeader = navigationView.getHeaderView(0).findViewById(R.id.ivNavHeader);
        spCourses = navigationView.getHeaderView(0).findViewById(R.id.spCourses);

        // Instantiating the custom spinner to change the Dropdown layout resources
        courseSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_course_titles,
                R.layout.custom_spinner_list_item);
        courseSpinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        spCourses.setAdapter(courseSpinnerAdapter);

        spCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                setIvNavHeader(Arrays.asList(getResources().getStringArray(
                        R.array.array_course_abb)).get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setIvNavHeader(getString(R.string.NA));
            }
        });

        //Fetching the Recycler views from the layout file
        blogsRecyclerView = (RecyclerView) findViewById(R.id.rv_blogs);
        announcementsRecyclerView = (RecyclerView) findViewById(R.id.rv_announcements);

        blogsRecyclerViewAdapter = new BlogsRecyclerViewAdapter(this);
        announcementsRecyclerViewAdapter = new AnnouncementsRecyclerViewAdapter(this);

        LinearLayoutManager blogsHorizontalLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager announcementsHorizontalLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        blogsRecyclerView.setLayoutManager(blogsHorizontalLinearLayoutManager);
        blogsRecyclerView.setAdapter(blogsRecyclerViewAdapter);

        announcementsRecyclerView.setLayoutManager(announcementsHorizontalLinearLayoutManager);
        announcementsRecyclerView.setAdapter(announcementsRecyclerViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
//         FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//         if(firebaseAuth.getCurrentUser()==null){
//             Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//             startActivity(intent);
//         }
    }
    private void setIvNavHeader(String text) {

        TextDrawable drawable = TextDrawable
                .builder()
                .beginConfig()
                .bold()
                .textColor(ContextCompat.getColor(this,R.color.colorPrimary))
                .toUpperCase()
                .withBorder(5)
                .endConfig().buildRound(text,ContextCompat.getColor(
                        this,R.color.navBarBackground));
        ivNavHeader.setImageDrawable(drawable);
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
        // Returning false so that the menu doesn't get inflated as the Settings are been implemented
        // through Navigation menu
        return false;
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

    // Sets the shadow for Toolbar and AppBarLayout
    private void setToolbarShadow(int id){
        AppBarLayout appBar = findViewById(R.id.appBar);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            // If Community section is opened, the AppBarLayout should have no shadows
            // to prevent shadow overlapping with TabLayout
            if (id == R.id.nav_community) {
                appBar.setElevation(0);
            } else {
                appBar.setElevation(6);
            }
        }
    }

    private void displaySelectedScreen(int id){

        Fragment fragment = null ;

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
            case R.id.nav_user_profile:
                Intent profileActivityIntent = new Intent(this, UserDetailsActivity.class);

                startActivity(profileActivityIntent);
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
        }

        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment).addToBackStack(null);
            ft.commit();
        }

        setToolbarShadow(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
