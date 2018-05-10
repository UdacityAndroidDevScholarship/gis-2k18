package com.udacity.googleindiascholarships.currentuser.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.udacity.googleindiascholarships.R;

/**
 * Created by HP on 05-05-2018.
 */

public class UserDetailsAboutTabFragment extends Fragment {

    private boolean editSkillFlag = false, editDescriptionFlag = false;
    EditText etUserSkills;
    ImageButton btnUserSkillEdit;
    EditText etUserDescription;
    ImageButton btnUserDescriptionEdit;


    public UserDetailsAboutTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details_about_tab, container, false);

        etUserSkills = view.findViewById(R.id.et_user_skills);
        btnUserSkillEdit = view.findViewById(R.id.btn_user_skill_edit);

        etUserDescription = view.findViewById(R.id.et_user_description_edit);
        btnUserDescriptionEdit = view.findViewById(R.id.btn_user_description_edit);

        setupButtonClickListenersForAboutSection();

        return view;
    }

    private void setupButtonClickListenersForAboutSection() {

        btnUserSkillEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editSkillFlag) {
                    etUserSkills.setFocusableInTouchMode(true);
                    etUserSkills.setEnabled(true);
                    etUserSkills.setSelection(etUserSkills.getText().toString().length());
                    btnUserSkillEdit.setImageResource(R.drawable.ic_tick_save);
                    editSkillFlag = true;
                } else {
                    etUserSkills.setFocusable(false);
                    etUserSkills.setEnabled(false);
                    btnUserSkillEdit.setImageResource(R.drawable.ic_edit);
                    editSkillFlag = false;

                }
            }
        });

        btnUserDescriptionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editDescriptionFlag) {
                    etUserDescription.setFocusableInTouchMode(true);
                    etUserDescription.setEnabled(true);
                    etUserDescription.setSelection(etUserDescription.getText().toString().length());
                    btnUserDescriptionEdit.setImageResource(R.drawable.ic_tick_save);
                    editDescriptionFlag = true;
                } else {
                    etUserDescription.setFocusable(false);
                    etUserDescription.setEnabled(false);
                    btnUserDescriptionEdit.setImageResource(R.drawable.ic_edit);
                    editDescriptionFlag = false;

                }
            }
        });
    }
}
