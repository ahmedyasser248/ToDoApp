package com.example.android.todoapp.database

import androidx.room.ColumnInfo

data class CountRecord(
    @ColumnInfo(name = "range")
    var week: Int,
    @ColumnInfo(name = "count")
    var count: Int)