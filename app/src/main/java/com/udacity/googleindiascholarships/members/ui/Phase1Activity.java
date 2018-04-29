package com.udacity.googleindiascholarships.members.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.members.ui.adapters.Phase1MembersAdapter;

public class Phase1Activity extends AppCompatActivity {

    RecyclerView rvPhase1Members;
    LinearLayoutManager llmPhase1Members;
    Phase1MembersAdapter phase1MembersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase1);

        rvPhase1Members = (RecyclerView) findViewById(R.id.rv_phase1_members);

        llmPhase1Members = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvPhase1Members.setLayoutManager(llmPhase1Members);

        phase1MembersAdapter = new Phase1MembersAdapter(this);
        rvPhase1Members.setAdapter(phase1MembersAdapter);

    }


}
