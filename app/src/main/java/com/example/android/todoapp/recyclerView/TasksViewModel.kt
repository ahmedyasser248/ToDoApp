package com.example.android.todoapp.recyclerView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.todoapp.database.AppDatabaseDao
import com.example.android.todoapp.database.Task

class TasksViewModel (val database :AppDatabaseDao, application: Application):AndroidViewModel(application) {
    // the tasks in database
    val tasks=database.getAllTasks()
    //selected task
    private val _navigateToTaskDetail =MutableLiveData<Long>()
    val navigateToTaskDetail
    get() = _navigateToTaskDetail

    fun onTaskClicked(id : Long){
        _navigateToTaskDetail.value=id
    }
    fun onTaskDetailNavigateComplete(){
        _navigateToTaskDetail.value=null
    }
    fun getColor(task: Task):Long{
        val category=database.getCategory(task.categoryId)
        return  category!!.categoryColor
    }
}
