package com.example.to_doapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.to_doapplication.data.Task
import com.example.to_doapplication.data.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTask : AppCompatActivity() {
    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var editTextDate: EditText
    private lateinit var editTextStartTime: EditText
    private lateinit var editTextEndTime: EditText
    private lateinit var editTextTags: EditText
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        editTextTitle = findViewById(R.id.editTextText4)
        editTextDescription = findViewById(R.id.editTextTextMultiLine2)
        editTextDate = findViewById(R.id.editTextDate2)
        editTextStartTime = findViewById(R.id.editTextTime4)
        editTextEndTime = findViewById(R.id.editTextTime3)
        editTextTags = findViewById(R.id.editTextText5)
        updateButton = findViewById(R.id.button6)

        val task = intent.getSerializableExtra("task") as Task

        editTextTitle.setText(task.title)
        editTextDescription.setText(task.des)
        editTextDate.setText(task.date)
        editTextStartTime.setText(task.timeS)
        editTextEndTime.setText(task.timeE)
        editTextTags.setText(task.tags)

        updateButton.setOnClickListener {
            updateTask(task)
        }
    }

    private fun updateTask(task: Task) {
        val title = editTextTitle.text.toString().trim()
        val description = editTextDescription.text.toString().trim()
        val date = editTextDate.text.toString().trim()
        val startTime = editTextStartTime.text.toString().trim()
        val endTime = editTextEndTime.text.toString().trim()
        val tags = editTextTags.text.toString().trim()

        if (title.isEmpty()) {
            editTextTitle.error = "Title required"
            editTextTitle.requestFocus()
            return
        }

        val updatedTask = Task(task.id, title, date, startTime, endTime, description, task.type, tags)
        val taskDao = TaskDatabase.getInstance(this).taskDao()

        // Use lifecycleScope.launch to handle coroutines with Android lifecycle
        lifecycleScope.launch(Dispatchers.IO) {
            taskDao.updateTask(updatedTask)
            // Use runOnUiThread to show Toast from background thread
            runOnUiThread {
                Toast.makeText(this@EditTask, "Successfully updated", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}
