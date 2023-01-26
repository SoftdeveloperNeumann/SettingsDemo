package com.example.settingsdemo.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.settingsdemo.R

class RootFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}