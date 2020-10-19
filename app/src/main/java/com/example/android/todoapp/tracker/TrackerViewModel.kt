package com.example.android.todoapp.tracker

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.todoapp.database.AppDatabaseDao
import java.util.*

class TrackerViewModel(application: Application, private val dataSource: AppDatabaseDao): AndroidViewModel(application)  {

    var year: Int = 0
    var month: Int = 0
    private val _isUpdated = MutableLiveData<Boolean>(true)
    val isUpdated: LiveData<Boolean>
        get() = _isUpdated
    lateinit var a12pplication: Application

    init {
        a12pplication = application
    }

    fun setTasksLineGraphDate(year: Int, month:Int){
        this.year = year
        this.month = month
        println("New Date " +year+ " "+month)
        _isUpdated.value = false
    }

    fun setIsUpdated() {
        _isUpdated.value = true
    }

    fun getLinesData(): Array<Array<Int>> {
        val cal = Calendar.getInstance()
        cal.set(year, month, 1)
        val monthStart = cal.timeInMillis
        cal.add(Calendar.MONTH, 1)
        val monthEnd = cal.timeInMillis
        val valuesAdded = dataSource.getTasksCountInMonthWeeks(monthStart, monthEnd)
        val tempEntries = Array<Int>(4) {0}
        for (value in valuesAdded) {
            if (value.week == 1)
                tempEntries[0]  = value.count
            else if (value.week == 2)
                tempEntries[1]  = value.count
            else if (value.week == 3)
                tempEntries[2]  = value.count
            else if (value.week == 4)
                tempEntries[3]  = value.count
        }
        val valuesFinished = dataSource.getTasksFinishedCountInMonthWeeks(monthStart, monthEnd)
        val tempEntries2 = Array<Int>(4) {0}
        for (value in valuesFinished) {
            if (value.week == 1)
                tempEntries2[0]  = value.count
            else if (value.week == 2)
                tempEntries2[1]  = value.count
            else if (value.week == 3)
                tempEntries2[2]  = value.count
            else if (value.week == 4)
                tempEntries2[3]  = value.count
        }
        return arrayOf(tempEntries, tempEntries2)
    }

}
