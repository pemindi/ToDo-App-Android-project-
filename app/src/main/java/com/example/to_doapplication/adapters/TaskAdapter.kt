package com.example.to_doapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doapplication.R
import com.example.to_doapplication.data.Task

class TaskAdapter(
    private val tasks: List<Task>,
    private val onDeleteClickListener: OnDeleteClickListener,
    private val onEditClickListener: OnEditClickListener
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    interface OnDeleteClickListener {
        fun onDeleteClick(task: Task)
    }

    interface OnEditClickListener {
        fun onEditClick(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        holder.bind(currentTask)
    }

    override fun getItemCount() = tasks.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val startTimeTextView: TextView = itemView.findViewById(R.id.textViewStartTime)
        private val endTimeTextView: TextView = itemView.findViewById(R.id.textViewEndTime)
        private val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        private val tagsTextView: TextView = itemView.findViewById(R.id.textViewTags)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.imageButton2)
        private val editButton: ImageButton = itemView.findViewById(R.id.imageButton1)

        fun bind(task: Task) {
            titleTextView.text = task.title
            startTimeTextView.text = task.timeS
            endTimeTextView.text = task.timeE
            dateTextView.text = task.date
            tagsTextView.text = task.tags

            // Set click listeners for delete and edit buttons
            deleteButton.setOnClickListener {
                onDeleteClickListener.onDeleteClick(task)
            }
            editButton.setOnClickListener {
                onEditClickListener.onEditClick(task)
            }
        }
    }
}
