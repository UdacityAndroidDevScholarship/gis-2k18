package com.udacity.googleindiascholarships.members.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.googleindiascholarships.R;

/**
 * Created by jha.anuj.2108 on 13-04-2018.
 */

public class MembersFragment extends android.support.v4.app.Fragment{

    Button btnPhase1,btnPhase2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_members, container, false);

        btnPhase1 = (Button) rootView.findViewById(R.id.btn_phase1);
        btnPhase2 = (Button) rootView.findViewById(R.id.btn_phase2);

        btnPhase1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Phase1Activity.class);
                startActivity(intent);
            }
        });

        btnPhase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Phase2Activity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Members");
    }
}
