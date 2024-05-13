package com.example.to_doapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private var tasks: List<Task>, private val context: Context) :
    RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private val db: TaskDataBaseHelper = TaskDataBaseHelper(context)

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewStartTime: TextView = itemView.findViewById(R.id.textViewStartTime)
        val textViewEndTime: TextView = itemView.findViewById(R.id.textViewEndTime)
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        val textViewPriority: TextView = itemView.findViewById(R.id.textViewPriority)
        val imageButtonedit: ImageView = itemView.findViewById(R.id.imageButtonedit)
        val imageButton2delete: ImageView = itemView.findViewById(R.id.imageButton2delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main6, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.textViewTitle.text = task.title
        holder.textViewStartTime.text = task.sTime
        holder.textViewEndTime.text = task.eTime
        holder.textViewDate.text = task.date
        holder.textViewPriority.text = task.priority.toString()

        holder.imageButtonedit.setOnClickListener {
            val intent = Intent(context, MainActivity8::class.java).apply {
                putExtra("task_id", task.id)
            }
            context.startActivity(intent)
        }

        holder.imageButton2delete.setOnClickListener {
            db.deleteTask(task.id)
            refreshdata(db.getAllTasks())
            Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshdata(newTask: List<Task>) {
        tasks = newTask
        notifyDataSetChanged()
    }
}
