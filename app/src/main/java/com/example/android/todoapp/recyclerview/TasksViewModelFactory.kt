package com.example.android.todoapp.recyclerview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.todoapp.database.AppDatabaseDao
import java.lang.IllegalArgumentException

class TasksViewModelFactory(
    private val dataSource :AppDatabaseDao ,
    private val application: Application):ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TasksViewModel::class.java)){
            return TasksViewModel(dataSource,application)as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}