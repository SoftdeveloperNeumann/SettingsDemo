package com.example.settingsdemo.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.settingsdemo.R

class SettingsActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_settings,RootFragment())
            .commit()
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat,
        pref: Preference
    ): Boolean {
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(classLoader,pref.fragment!!)
        fragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_settings,fragment)
            .addToBackStack(null)
            .commit()

        supportFragmentManager.setFragmentResultListener("xy",fragment){key,bundle ->
            if(key == "xy"){
                supportFragmentManager.setFragmentResult("xy", bundle)
            }
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        if(supportFragmentManager.popBackStackImmediate()){
            return true
        }
        return super.onSupportNavigateUp()
    }
}