package com.example.to_doapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageButton

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        // Find the arrow, dashboard, and add task buttons by their IDs
        val arrowButton: ImageButton = findViewById(R.id.imageButton11)
        val dashboardButton: ImageButton = findViewById(R.id.imageButton2)
        val addTaskButton: ImageButton = findViewById(R.id.imageButton)

        // Set OnClickListener for the arrow button to navigate to ViewAllTasksActivity
        arrowButton.setOnClickListener {
            val intent = Intent(this, ViewAllTasks::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for the dashboard button to reload MainActivity3
        dashboardButton.setOnClickListener {
            // You can simply recreate the activity to reload it
            recreate()
        }

        // Set OnClickListener for the add task button to navigate to AddTaskActivity
        addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }

    }
}