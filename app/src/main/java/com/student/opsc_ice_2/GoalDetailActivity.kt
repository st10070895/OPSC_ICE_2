package com.student.opsc_ice_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GoalDetailActivity : AppCompatActivity() {

    private lateinit var goalTitle: TextView
    private lateinit var goalDescription: TextView
    private lateinit var goalCompleted: CheckBox
    private lateinit var backButton: Button // Updated to backButton
    private var goalPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_detail)

        goalTitle = findViewById(R.id.textViewGoalTitle)
        goalDescription = findViewById(R.id.textViewGoalDescription)
        goalCompleted = findViewById(R.id.checkBoxGoalCompleted)
        backButton = findViewById(R.id.btnHome) // Updated to reflect the button ID

        val title = intent.getStringExtra("goalTitle")
        val description = intent.getStringExtra("goalDescription")
        val isCompleted = intent.getBooleanExtra("goalCompleted", false)
        goalPosition = intent.getIntExtra("goalPosition", -1)

        goalTitle.text = title
        goalDescription.text = description
        goalCompleted.isChecked = isCompleted

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        val intent = Intent().apply {
            putExtra("goalPosition", goalPosition)
            putExtra("goalCompleted", goalCompleted.isChecked)
        }
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}
