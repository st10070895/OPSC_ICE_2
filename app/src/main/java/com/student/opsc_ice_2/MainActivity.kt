package com.student.opsc_ice_2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val goalsList = mutableListOf<Triple<String, String, Boolean>>()
    private lateinit var goalsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load theme from shared preferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        AppCompatDelegate.setDefaultNightMode(if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_main)

        goalsRecyclerView = findViewById(R.id.recyclerViewGoals)
        goalsRecyclerView.layoutManager = LinearLayoutManager(this)
        goalsRecyclerView.adapter = GoalsRecyclerAdapter()

        val addGoalButton = findViewById<Button>(R.id.btnAddGoal)
        addGoalButton.setOnClickListener {
            val title = findViewById<EditText>(R.id.editTextGoalTitle).text.toString()
            val description = findViewById<EditText>(R.id.editTextGoalDescription).text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                goalsList.add(Triple(title, description, false))
                goalsRecyclerView.adapter?.notifyDataSetChanged()
            }
        }

        val settingsButton = findViewById<Button>(R.id.btnSettings)
        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    inner class GoalsRecyclerAdapter : RecyclerView.Adapter<GoalsRecyclerAdapter.GoalViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
            val view = layoutInflater.inflate(R.layout.item_goal, parent, false)
            return GoalViewHolder(view)
        }

        override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
            val goal = goalsList[position]
            holder.bind(goal)
            holder.itemView.setOnClickListener {
                val intent = Intent(this@MainActivity, GoalDetailActivity::class.java).apply {
                    putExtra("goalTitle", goal.first)
                    putExtra("goalDescription", goal.second)
                }
                startActivity(intent)
            }
        }

        override fun getItemCount(): Int = goalsList.size

        inner class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val goalTitle: TextView = itemView.findViewById(R.id.textViewGoalTitle)
            private val goalDescription: TextView = itemView.findViewById(R.id.textViewGoalDescription)
            private val goalCompleted: CheckBox = itemView.findViewById(R.id.checkBoxGoalCompleted)

            fun bind(goal: Triple<String, String, Boolean>) {
                goalTitle.text = goal.first
                goalDescription.text = goal.second
                goalCompleted.isChecked = goal.third

                goalCompleted.setOnCheckedChangeListener { _, isChecked ->
                    goalsList[adapterPosition] = Triple(goal.first, goal.second, isChecked)
                }
            }
        }
    }
}