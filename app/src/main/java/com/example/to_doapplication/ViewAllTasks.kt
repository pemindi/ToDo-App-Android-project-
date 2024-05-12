package com.example.to_doapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doapplication.adapters.TaskAdapter
import com.example.to_doapplication.data.Task
import com.example.to_doapplication.data.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewAllTasks : AppCompatActivity(), TaskAdapter.OnDeleteClickListener, TaskAdapter.OnEditClickListener {

    private lateinit var recyclerViewTasks: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_tasks)

        // Find the dashboard, and add task buttons by their IDs
        val dashboardButton: ImageButton = findViewById(R.id.imageButton7)
        val addTaskButton: ImageButton = findViewById(R.id.imageButton10)

        // Set OnClickListener for the dashboard button to reload MainActivity3
        dashboardButton.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for the add task button to navigate to AddTaskActivity
        addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }

        // Initialize RecyclerView
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks)
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        // Load tasks from the database
        val taskDao = TaskDatabase.getInstance(this).taskDao()

        // Observe the LiveData
        taskDao.getAllTasks().observe(this, Observer<List<Task>> { tasks ->
            // Update the adapter with the new data
            taskAdapter = TaskAdapter(tasks, this, this)
            recyclerViewTasks.adapter = taskAdapter
        })
    }

    override fun onDeleteClick(task: Task) {
        // Show an alert dialog for confirmation
        AlertDialog.Builder(this)
            .setTitle("Delete Task")
            .setMessage("Are you sure you want to delete this task?")
            .setPositiveButton("Delete") { _, _ ->
                // Delete the task from the database
                lifecycleScope.launch(Dispatchers.IO) {
                    deleteTask(task)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onEditClick(task: Task) {
        // Open EditTaskActivity with the selected task
        val intent = Intent(this, EditTask::class.java)

        // Create a bundle to store the task details
        val bundle = Bundle()
        bundle.putString("title", task.title)
        bundle.putString("timeS", task.timeS)
        bundle.putString("timeE", task.timeE)
        bundle.putString("date", task.date)
        bundle.putString("tags", task.tags)

        // Put the bundle in the intent
        intent.putExtras(bundle)

        // Start the activity
        startActivity(intent)
    }


    private suspend fun deleteTask(task: Task) {
        val taskDao = TaskDatabase.getInstance(this).taskDao()
        taskDao.deleteTask(task)
        // Notify the user with a toast message
        // Toast.makeText(this, "Task successfully removed", Toast.LENGTH_SHORT).show()
    }
}
