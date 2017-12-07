package com.sagunpandey.ews;

import android.preference.PreferenceActivity;

import java.util.List;

/**
 * Created by sagun on 6/25/2017.
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SettingsPreferencesFragment.class.getName().equals(fragmentName) || SettingsAboutFragment.class.getName().equals(fragmentName);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
//        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsPreferencesFragment()).commit();
        loadHeadersFromResource(R.xml.settings_headers, target);
    }
}
