package com.udacity.googleindiascholarships.ui;

import android.content.Context;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

import com.udacity.googleindiascholarships.R;

/**
 * Created by Sudhanshu on 09-05-2018.
 */

public class SignOutDialogPreference extends DialogPreference {

    int mDialogLayoutResourceId = R.layout.pref_dialog_sign_out;

    public SignOutDialogPreference(Context context){
        super(context, null);
        setDialogLayoutResource(mDialogLayoutResourceId);
        setDialogIcon(R.drawable.gis_logo);
        setKey(context.getString(R.string.pref_dialog_key));
    }

    public SignOutDialogPreference(Context context, AttributeSet attrs){
        super(context, attrs);
        setDialogLayoutResource(mDialogLayoutResourceId);
        setDialogIcon(R.drawable.gis_logo);
        setKey(context.getString(R.string.pref_dialog_key));
    }

    public SignOutDialogPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setDialogLayoutResource(mDialogLayoutResourceId);
        setDialogIcon(R.drawable.gis_logo);
        setKey(context.getString(R.string.pref_dialog_key));
    }

    @Override
    public int getDialogLayoutResource() {
        return mDialogLayoutResourceId;
    }
}
