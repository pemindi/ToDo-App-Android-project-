package com.example.to_doapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.to_doapplication.ViewModel.TaskViewModel
import com.example.to_doapplication.data.Task

class AddTask : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        // Initialize TaskViewModel with ViewModelProvider
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Retrieve references to UI elements
        val titleEditText: EditText = findViewById(R.id.editTextText4)
        val descriptionEditText: EditText = findViewById(R.id.editTextTextMultiLine2)
        val dateEditText: EditText = findViewById(R.id.editTextDate2)
        val startTimeEditText: EditText = findViewById(R.id.editTextTime4)
        val endTimeEditText: EditText = findViewById(R.id.editTextTime3)
        val typeEditText: EditText = findViewById(R.id.editTextText6)
        val tagsEditText: EditText = findViewById(R.id.editTextText5)
        val addButton: Button = findViewById(R.id.button6)

        // Set up click listener for the Add button
        addButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()
            val date = dateEditText.text.toString().trim()
            val startTime = startTimeEditText.text.toString().trim()
            val endTime = endTimeEditText.text.toString().trim()
            val type = typeEditText.text.toString().trim()
            val tags = tagsEditText.text.toString().trim()

            if (title.isNotEmpty()) {
                // Create a Task object with the entered data
                val task = Task(
                    title = title,
                    date = date,
                    timeS = startTime,
                    timeE = endTime,
                    des = description,
                    type = type,
                    tags = tags
                )

                // Insert the task into the database using the ViewModel
                taskViewModel.insertTask(task)

                // Display a toast message indicating successful insertion
                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_SHORT).show()

                // Clear the input fields
                titleEditText.text.clear()
                descriptionEditText.text.clear()
                dateEditText.text.clear()
                startTimeEditText.text.clear()
                endTimeEditText.text.clear()
                typeEditText.text.clear()
                tagsEditText.text.clear()
            } else {
                // Show a toast message if the title is empty
                Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe LiveData for database changes
        taskViewModel.inserted.observe(this, Observer {
            // You can handle changes here if needed
        })
    }
}
