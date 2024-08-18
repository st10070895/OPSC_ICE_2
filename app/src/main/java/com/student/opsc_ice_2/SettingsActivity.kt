package com.student.opsc_ice_2

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var backButton: Button // Changed to backButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        backButton = findViewById(R.id.btnHome) // Update to reflect the new role

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

        backButton.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }
    }
}
