package com.example.android.todoapp.database
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDatabaseDao {

    @Insert
    fun insert(task: Task)

    @Insert
    fun insert(category: Category)

    @Update
    fun update(task: Task)

    @Update
    fun update(category: Category)

    @Query("SELECT * from task_table WHERE task_id = :taskId")
    fun getTask(taskId: Long): Task?

    @Query("SELECT * from category_table WHERE category_id = :categoryId")
    fun getCategory(categoryId: Long): Category?

    @Query("DELETE FROM task_table")
    fun clearTasks()

    @Query("DELETE FROM category_table")
    fun clearCategories()

    @Query("SELECT * FROM task_table ORDER BY task_id DESC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM category_table ORDER BY category_id DESC")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM task_table Where task_category = :categoryId ORDER BY task_id DESC")
    fun getTasksWithCategory(categoryId: Long): LiveData<List<Task>>

    @Query("SELECT * FROM task_table Where task_listing_time >= :listingTime AND task_dequeue_time <= :dequeueTime ORDER BY task_id DESC")
    fun getTasksWithDateRange(listingTime: Long, dequeueTime: Long): LiveData<List<Task>>

}
