package com.student.opsc_ice_2

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        val toggleDarkModeButton = findViewById<Button>(R.id.switchDarkMode)
        toggleDarkModeButton.setOnClickListener {
            val currentMode = sharedPreferences.getBoolean("dark_mode", false)
            val editor = sharedPreferences.edit()
            editor.putBoolean("dark_mode", !currentMode)
            editor.apply()

            AppCompatDelegate.setDefaultNightMode(
                if (currentMode) AppCompatDelegate.MODE_NIGHT_NO
                else AppCompatDelegate.MODE_NIGHT_YES
            )
            recreate()
        }
    }
}