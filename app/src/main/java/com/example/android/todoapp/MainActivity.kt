package com.example.android.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.todoapp.database.AppDatabase
import com.example.android.todoapp.database.AppDatabaseDao

import com.example.android.todoapp.recyclerView.TasksViewModel
import com.example.android.todoapp.recyclerView.TasksViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }
}