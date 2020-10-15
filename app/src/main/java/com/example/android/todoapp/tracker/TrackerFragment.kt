package com.example.android.todoapp.tracker

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.todoapp.R
import com.example.android.todoapp.database.AppDatabase
import com.example.android.todoapp.database.AppDatabaseDao
import com.example.android.todoapp.database.Category
import com.example.android.todoapp.databinding.TasksrecylerviewBinding
import com.example.android.todoapp.databinding.TrackerFragmentBinding
import com.example.android.todoapp.recyclerview.TaskListener
import com.example.android.todoapp.recyclerview.TasksAdapter
import com.example.android.todoapp.recyclerview.TasksViewModel
import com.example.android.todoapp.recyclerview.TasksViewModelFactory
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.tracker_fragment.*
import kotlinx.coroutines.*
import java.util.*

class TrackerFragment : Fragment() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        println("Iam Here")
        val binding : TrackerFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.tracker_fragment,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).appDatabaseDao
        binding.lifecycleOwner=this


        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        println(cal.timeInMillis)
        cal.add(Calendar.WEEK_OF_MONTH, 1)
        println(cal.timeInMillis)
        cal.add(Calendar.WEEK_OF_MONTH, 1)
        println(cal.timeInMillis)
        cal.add(Calendar.WEEK_OF_MONTH, 1)
        println(cal.timeInMillis)
        cal.add(Calendar.WEEK_OF_MONTH, 1)
        println(cal.timeInMillis)



        cal.set(Calendar.DAY_OF_MONTH, 1)
        println(cal)
        cal.add(Calendar.MONTH, 1)

        uiScope.launch {
            println("arg " + cal.timeInMillis.toString())
            get(cal.timeInMillis, dataSource, binding)
        }




        return binding.root
    }

    private suspend fun get(timeSec: Long, database: AppDatabaseDao, binding: TrackerFragmentBinding){
        withContext(Dispatchers.IO) {
            val v = database.getTasksInfo()
            for (task in v) {
                println(task.taskId.toString() + " " + task.title + " " + task.dequeueTime + " " + task.status)
            }
            val values = database.getTasksCountInMonthWeeks(timeSec)
            val values2 = database.getTasksFinishedCountInMonthWeeks(timeSec)
            val entries1 = values.mapIndexed { index, arrayList ->
                Entry(index.toFloat(), values[index].toFloat()) }
            val entries2 = values2.mapIndexed { index, arrayList ->
                Entry(index.toFloat(), values[index].toFloat()) }

            val lineChartView = binding.lineChartView
            val lineDataSet1 = LineDataSet(entries1, "Added Tasks")
            lineDataSet1.color = Color.RED
            lineDataSet1.setDrawValues(false)
            lineDataSet1.setAxisDependency(YAxis.AxisDependency.LEFT)

            val lineDataSet2 = LineDataSet(entries2, "Finished Tasks")
            lineDataSet2.color = Color.BLUE
            lineDataSet2.setDrawValues(false)
            lineDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT)

            val lineDataSets = arrayListOf(lineDataSet1, lineDataSet2)
            lineChartView.data = LineData(lineDataSets as List<ILineDataSet>?)
            lineChartView.axisLeft.mAxisMaximum = 3f
            lineChartView.axisLeft.mAxisMinimum = -1f
            lineChartView.axisLeft.mAxisRange = 5f
            println("data added")
            lineChartView.invalidate()
        }
    }
}