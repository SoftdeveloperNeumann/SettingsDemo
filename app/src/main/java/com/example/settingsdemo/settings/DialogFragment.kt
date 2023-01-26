package com.example.settingsdemo.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.settingsdemo.R

class DialogFragment: PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.dialog_preferences,rootKey)
    }
}