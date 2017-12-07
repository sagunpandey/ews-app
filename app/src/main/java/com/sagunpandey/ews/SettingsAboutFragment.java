package com.sagunpandey.ews;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by sagun on 7/29/2017.
 */

public class SettingsAboutFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_about_fragment);
    }
}
