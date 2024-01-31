package com.divyanshu.preferenceapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.divyanshu.preferenceapp.databinding.ActivityMainScreenBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private val preferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        mySettings()
    }

    override fun onResume() {
        super.onResume()
        mySettings()
    }

    private fun mySettings() {
        binding.apply {
            txtSignature.text = preferences.getString("signature", "")
            txtSwitch.text = preferences.getBoolean("switch", false).toString()
            txtCheckBox.text = preferences.getBoolean("check_box_preference", false).toString()
            txtThemeMode.text =
                resources.getStringArray(R.array.dark_mode_entries)[preferences.getString(
                    "theme_mode",
                    "0"
                )?.toInt() ?: 0]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> startActivity(Intent(this, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}