package com.example.settingsdemo.settings

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.EditTextPreferenceDialogFragmentCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.settingsdemo.R

// Interface ist nicht notwendig, es dient dazu um von einer Preferenz zu anderen zu gelangen
class SettingsActivity : AppCompatActivity(),
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_settings, RootFragment())
            .commit()
    }

    // Funktion aus dem Interface
    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat,
        pref: Preference
    ): Boolean {
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment!!
        ) as PreferenceFragmentCompat
        args.putString("name", "Frank")
        fragment.arguments = args
        // Zeile ist notwendig,um den key zu prüfen
        supportFragmentManager.setFragmentResult("Dialog Prefs", args)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_settings, fragment)
            .addToBackStack(null)
            .commit()
        Log.d("TAG", "onPreferenceStartFragment:${pref.key} ")
        supportFragmentManager.setFragmentResultListener(pref.key, fragment) { key, bundle ->
            if (key == "Dialog Prefs") {
// der username muss gesetzt sein, damit in den Dialog Prefs einstellungen vorgenommen werden können
                if (caller.findPreference<EditTextPreference>("username")?.text == "empty") {
                    onClick(caller)
                    Toast.makeText(this, "Bitte erst den Usernamen festlegen", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }

        return true
    }

    fun onClick(caller: PreferenceFragmentCompat) {
        val p = caller.preferenceManager.findPreference<EditTextPreference>("username")

        val dialogFragment = EditTextPreferenceDialogFragmentCompat.newInstance(p!!.key)
        dialogFragment.setTargetFragment(caller, 0) // hier muss ein anderer Weg gefunden werden, das RootFragment zu benutzen
        dialogFragment.show(caller.parentFragmentManager, p.key)
        Log.d("TAG", "onClick: ich bin drin ${dialogFragment.targetFragment!!.javaClass.simpleName}")
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.popBackStackImmediate()) {
            return true
        }
        return super.onSupportNavigateUp()
    }
}