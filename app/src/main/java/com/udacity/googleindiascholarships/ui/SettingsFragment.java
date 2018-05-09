package com.udacity.googleindiascholarships.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.udacity.googleindiascholarships.R;

/**
 * Created by Sudhanshu on 09-05-2018.
 */

public class SettingsFragment extends PreferenceFragmentCompat{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_settings);
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // Try if the preference is one of our custom Preferences
        DialogFragment dialogFragment = null;
        if (preference instanceof SignOutDialogPreference) {
            // Create a new instance of SignOutPreferenceFragmentCompat with the key of the related
            // Preference
            dialogFragment = SignOutPreferenceFragmentCompat
                    .newInstance(preference.getKey());
        }

        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(this.getFragmentManager(),
                    "android.support.v7.preference" +
                            ".PreferenceFragment.DIALOG");
        }
        else {
            super.onDisplayPreferenceDialog(preference);
        }
    }
}
