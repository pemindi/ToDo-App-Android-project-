package com.example.to_doapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import kotlinx.coroutines.selects.select

class TaskDataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "tasks.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "alltasks"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_STIME = "sTime"
        private const val COLUMN_ETIME = "eTime"
        private const val COLUMN_PRIORITY = "priority"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_TITLE TEXT,$COLUMN_CONTENT TEXT,$COLUMN_DATE TEXT,$COLUMN_STIME TEXT,$COLUMN_ETIME TEXT,$COLUMN_PRIORITY INTEGER)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertTask(task: Task) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, task.title)
            put(COLUMN_CONTENT, task.content)
            put(COLUMN_DATE, task.date)
            put(COLUMN_STIME, task.sTime)
            put(COLUMN_ETIME, task.eTime)
            put(COLUMN_PRIORITY, task.priority)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllTasks(): List<Task> {

        val tasksList = mutableListOf<Task>()
        val db = readableDatabase
        val query= "SELECT*FROM $TABLE_NAME"
        val cursor=db.rawQuery(query,null)

        while (cursor.moveToNext()){

            val id=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
            val date=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
            val sTime=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STIME))
            val eTime=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ETIME))
            val priority=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRIORITY))

            val task=Task(id,title, content,date,sTime,eTime,priority)
            tasksList.add(task)
        }
        cursor.close()
        db.close()
        return tasksList
    }

    fun updateTask(task: Task){
        val db=writableDatabase
        val values= ContentValues().apply {
            put(COLUMN_TITLE, task.title)
            put(COLUMN_CONTENT, task.content)
            put(COLUMN_DATE, task.date)
            put(COLUMN_STIME, task.sTime)
            put(COLUMN_ETIME, task.eTime)
            put(COLUMN_PRIORITY, task.priority)
        }

        val whereClause="$COLUMN_ID=?"
        val whereArgs= arrayOf(task.id.toString())
        db.update(TABLE_NAME,values,whereClause,whereArgs)
        db.close()
    }

    fun getTaskByID(taskId:Int):Task{
        val db=readableDatabase
        val query="SELECT*FROM $TABLE_NAME WHERE $COLUMN_ID=$taskId"
        val cursor=db.rawQuery(query,null)
        cursor.moveToFirst()


        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
        val date=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
        val sTime=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STIME))
        val eTime=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ETIME))
        val priority=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRIORITY))

        cursor.close()
        db.close()
        return Task(id, title, content, date, sTime, eTime, priority)
    }

    fun deleteTask(taskId: Int){

        val db=writableDatabase
        val whereClause="$COLUMN_ID=?"
        val whereArgs= arrayOf(taskId.toString())
        db.delete(TABLE_NAME,whereClause,whereArgs)
        db.close()
    }
}
