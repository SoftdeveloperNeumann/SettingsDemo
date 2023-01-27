package com.example.settingsdemo

import android.content.Intent
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
    private  val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefs = PreferenceManager.getDefaultSharedPreferences(application)

        val values = prefs.all

        for((key,value) in values){
            Log.d(TAG, "onCreate: $key -> $value")
        }

        binding.tvOutput.text = prefs.getString("user_name","defaultwert")

        binding.btnSetName.setOnClickListener {
            prefs.edit().putString("user_name", binding.etName.text.toString())
            .apply()
            binding.tvOutput.text = prefs.getString("user_name","Default")
        }

        binding.btnDelete.setOnClickListener {
            prefs.edit().remove("user_name").apply()
            binding.tvOutput.text = prefs.getString("user_name","Default")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings ->{
                startActivity(Intent(this,SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}