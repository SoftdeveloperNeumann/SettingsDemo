package com.example.settingsdemo.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.settingsdemo.R


class AdvancedFragment:PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.advanced_preferences,rootKey)
    }
}