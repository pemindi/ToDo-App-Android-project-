package com.example.to_doapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val date: String,
    val timeS: String,
    val timeE: String,
    val des: String,
    val type: String,
    val tags: String

)