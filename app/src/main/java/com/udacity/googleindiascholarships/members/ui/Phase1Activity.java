package com.udacity.googleindiascholarships.members.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.members.ui.adapters.Phase1MembersAdapter;

public class Phase1Activity extends AppCompatActivity {

    RecyclerView rvPhase1Members;
    LinearLayoutManager llmPhase1Members;
    Phase1MembersAdapter phase1MembersAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase1);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("Phase 2");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        rvPhase1Members = (RecyclerView) findViewById(R.id.rv_phase1_members);

        llmPhase1Members = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvPhase1Members.setLayoutManager(llmPhase1Members);

        phase1MembersAdapter = new Phase1MembersAdapter(this);
        rvPhase1Members.setAdapter(phase1MembersAdapter);

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
