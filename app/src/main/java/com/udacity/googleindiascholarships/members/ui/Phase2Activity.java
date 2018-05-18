package com.udacity.googleindiascholarships.members.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.members.ui.adapters.Phase2MembersAdapter;

public class Phase2Activity extends AppCompatActivity {

    RecyclerView rvPhase2Members;
    LinearLayoutManager llmPhase2Members;
    Phase2MembersAdapter phase2MembersAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase2);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("Phase 2");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        rvPhase2Members = (RecyclerView) findViewById(R.id.rv_phase2_members);

        llmPhase2Members = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPhase2Members.setLayoutManager(llmPhase2Members);

        phase2MembersAdapter = new Phase2MembersAdapter(this);
        rvPhase2Members.setAdapter(phase2MembersAdapter);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
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
        return super.onCreateOptionsMenu(menu);
    }
}
