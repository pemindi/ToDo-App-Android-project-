package com.example.to_doapplication

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.to_doapplication.data.TaskDatabase

class MyApplication : Application() {

    lateinit var taskDatabase: TaskDatabase

    override fun onCreate() {
        super.onCreate()
        initRoomDatabase()
    }

    private fun initRoomDatabase() {
        taskDatabase = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task_database"
        )
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        // Migration from version 1 to version 2 (if schema changes are needed)
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Define the migration logic here
                // For example, alter table, add columns, etc.
            }
        }
    }
}
