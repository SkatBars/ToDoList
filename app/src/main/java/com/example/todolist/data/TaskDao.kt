package com.example.todolist.data

import androidx.room.*
import java.util.*

@Dao
interface TaskDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * from tasks")
    suspend fun getTasksByDate(): List<Task>
}