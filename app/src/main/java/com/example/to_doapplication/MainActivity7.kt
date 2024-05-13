package com.example.to_doapplication

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity7 : AppCompatActivity() {

    private lateinit var db: TaskDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        db = TaskDataBaseHelper(this)

        val addButton: Button = findViewById(R.id.button6)
        addButton.setOnClickListener {
            addTask()
        }
    }

    private fun addTask() {
        val titleEditText: EditText = findViewById(R.id.editTextText4)
        val contentEditText: EditText = findViewById(R.id.editTextTextMultiLine2)
        val dateEditText: EditText = findViewById(R.id.editTextDate2)
        val sTimeEditText: EditText = findViewById(R.id.editTextTime4)
        val eTimeEditText: EditText = findViewById(R.id.editTextTime3)
        val priorityEditText: EditText = findViewById(R.id.editTextText6)

        val title = titleEditText.text.toString().trim()
        val content = contentEditText.text.toString().trim()
        val date =dateEditText.text.toString().trim()
        val sTime = sTimeEditText.text.toString().trim()
        val eTime = eTimeEditText.text.toString().trim()
        val priorityStr = priorityEditText.text.toString().trim()

        if (title.isNotEmpty() && content.isNotEmpty() && date.isNotEmpty() && sTime.isNotEmpty() && eTime.isNotEmpty() && priorityStr.isNotEmpty()) {
            val priority = priorityStr.toIntOrNull()
            if (priority != null) {
                val task = Task(0, title, content, date , sTime, eTime, priority)
                db.insertTask(task)
                Toast.makeText(this, "Task successfully added", Toast.LENGTH_SHORT).show()

                // Clear EditText fields after successful addition
                titleEditText.text.clear()
                contentEditText.text.clear()
                sTimeEditText.text.clear()
                eTimeEditText.text.clear()
                priorityEditText.text.clear()

                // Redirect to MainActivity3
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
                finish() // Finish the current activity
            } else {
                Toast.makeText(this, "Priority should be a number", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
