package com.example.settingsdemo

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.preference.PreferenceManager
import com.example.settingsdemo.databinding.ActivityMainBinding
import com.example.settingsdemo.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = PreferenceManager.getDefaultSharedPreferences(application)
        val setColor = prefs.getBoolean("check_box_preference_1",false)
        val values = prefs.all

        for ((key, value) in values) {
            Log.d(TAG, "onCreate: $key -> $value")
        }

        if(setColor){
            binding.root.setBackgroundColor(Color.RED)
        }else{
            binding.root.setBackgroundColor(Color.WHITE)
        }

        binding.tvOutput.text = prefs.getString("username", "defaultwert")

        binding.btnSetName.setOnClickListener {
            prefs.edit().putString("username", binding.etName.text.toString())
                .apply()
            binding.tvOutput.text = prefs.getString("username", "Default")
        }

        binding.btnDelete.setOnClickListener {
            prefs.edit().remove("username").apply()
            binding.tvOutput.text = prefs.getString("username", "Default")
        }



    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}