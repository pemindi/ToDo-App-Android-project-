package com.example.to_doapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TaskRepository(private val taskDao: TaskDao) {

    // LiveData to observe the insertion process
    private val _inserted = MutableLiveData<Unit>()
    val inserted: LiveData<Unit>
        get() = _inserted

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
        // Notify observers that a task has been inserted
        _inserted.postValue(Unit)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

    fun getTaskById(taskId: Long): LiveData<Task?> {
        return taskDao.getTaskById(taskId)
    }
}
