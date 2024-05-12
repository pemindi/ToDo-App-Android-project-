package com.example.to_doapplication.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import com.example.to_doapplication.data.Task
import com.example.to_doapplication.data.TaskRepository

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val inserted: LiveData<Unit> = repository.inserted

    fun insertTask(task: Task) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return repository.getAllTasks()
    }

    fun getTaskById(taskId: Long): LiveData<Task?> {
        return repository.getTaskById(taskId)
    }
}
