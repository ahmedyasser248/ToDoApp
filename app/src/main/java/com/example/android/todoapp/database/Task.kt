package com.example.android.todoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName="task_table", foreignKeys = arrayOf(
    ForeignKey(entity = Category::class,
        parentColumns = arrayOf("category_id"),
        childColumns = arrayOf("task_category"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)
)
)

data class Task  (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    var taskId: Long = 0L,

    @ColumnInfo(name = "task_title")
    var title: String = "",

    @ColumnInfo(name = "task_description")
    var description: String = "",

    @ColumnInfo(name = "task_status")
    var status: Short = 0,

    @ColumnInfo(name = "task_listing_time")
    val listingTime: Long =  System.currentTimeMillis(),

    @ColumnInfo(name = "task_dequeue_time")
    val dequeueTime: Long =  0L,

    @ColumnInfo(name = "task_category")
    var categoryId: Long = 0L
)