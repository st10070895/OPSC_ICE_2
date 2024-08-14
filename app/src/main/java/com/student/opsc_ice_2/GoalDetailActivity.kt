package com.student.opsc_ice_2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GoalDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_detail)

        val goalTitle = intent.getStringExtra("goalTitle")
        val goalDescription = intent.getStringExtra("goalDescription")

        findViewById<TextView>(R.id.textViewGoalTitle).text = goalTitle
        findViewById<TextView>(R.id.textViewGoalDescription).text = goalDescription
    }
}