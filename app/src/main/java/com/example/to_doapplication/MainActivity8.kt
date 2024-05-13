package com.example.to_doapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity8 : AppCompatActivity() {

    private lateinit var db: TaskDataBaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        db = TaskDataBaseHelper(this)
        taskId = intent.getIntExtra("task_id", -1)

        if (taskId == -1) {
            finish()
            return
        }

        val task = db.getTaskByID(taskId)

        // Initialize EditTexts
        val titleEditText: EditText = findViewById(R.id.editTextText4)
        val contentEditText: EditText = findViewById(R.id.editTextTextMultiLine2)
        val dateEditText: EditText = findViewById(R.id.editTextDate2)
        val sTimeEditText: EditText = findViewById(R.id.editTextTime4)
        val eTimeEditText: EditText = findViewById(R.id.editTextTime3)
        val priorityEditText: EditText = findViewById(R.id.editTextText6)

        // Set current task details to EditTexts
        titleEditText.setText(task.title)
        contentEditText.setText(task.content)
        dateEditText.setText(task.date)
        sTimeEditText.setText(task.sTime)
        eTimeEditText.setText(task.eTime)
        priorityEditText.setText(task.priority.toString())

        // Find the "Edit Task" button
        val editButton: Button = findViewById(R.id.button6)

        // Set click listener for the "Edit Task" button
        editButton.setOnClickListener {
            // Get updated task details from EditTexts
            val updatedTitle = titleEditText.text.toString().trim()
            val updatedContent = contentEditText.text.toString().trim()
            val updatedDate = dateEditText.text.toString().trim()
            val updatedStartTime = sTimeEditText.text.toString().trim()
            val updatedEndTime = eTimeEditText.text.toString().trim()
            val updatedPriorityStr = priorityEditText.text.toString().trim()

            if (updatedTitle.isNotEmpty() && updatedContent.isNotEmpty() && updatedDate.isNotEmpty() &&
                updatedStartTime.isNotEmpty() && updatedEndTime.isNotEmpty() && updatedPriorityStr.isNotEmpty()) {
                // Convert priority to Int
                val updatedPriority = updatedPriorityStr.toIntOrNull() ?: 0

                // Update task in the database
                val updatedTask = Task(taskId, updatedTitle, updatedContent, updatedDate, updatedStartTime, updatedEndTime, updatedPriority)
                db.updateTask(updatedTask)

                // Show toast message
                Toast.makeText(this, "Updated task", Toast.LENGTH_SHORT).show()

                // Redirect to MainActivity5
                val intent = Intent(this, MainActivity5::class.java)
                startActivity(intent)
                finish() // Finish the current activity
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
