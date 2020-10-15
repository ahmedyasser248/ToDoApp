package com.example.android.todoapp.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.todoapp.R
import com.example.android.todoapp.database.AppDatabase
import com.example.android.todoapp.database.Category
import com.example.android.todoapp.database.Task
import com.example.android.todoapp.databinding.TasksrecylerviewBinding
import kotlinx.coroutines.*

class TasksFragment : Fragment() {
    val scope= CoroutineScope(Job()+Dispatchers.IO)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : TasksrecylerviewBinding=DataBindingUtil.inflate(inflater, R.layout.tasksrecylerview,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AppDatabase.getInstance(application).appDatabaseDao


        val viewModelFactory = TasksViewModelFactory(dataSource,application)

        val tasksViewModel = ViewModelProvider(this,viewModelFactory).get(TasksViewModel::class.java)

        binding.lifecycleOwner=this

        val adapter = TasksAdapter(TaskListener { taskId ->tasksViewModel.onTaskClicked(taskId)  },dataSource)


        binding.TasksList.adapter=adapter
        val category=Category(0,"hello")

        val task1=Task(0,"last try","hello, my name is ahmed yasser",0,4412,12443,1)
        scope.launch {
            withContext(Dispatchers.IO) {
                Log.i("5th mesage","i got here also")
                dataSource.insert(category)
                dataSource.insert(task1)


            }
        }

        tasksViewModel.tasks.observe(viewLifecycleOwner, Observer {
          it?.let{

             adapter.submitList(it)

           }
        })

        Log.i("2nd messgae","i completed it")
        binding.RoundedButton.setOnClickListener {v->
            v.findNavController().navigate(R.id.add_task_fragment)

        }

        return binding.root
    }

}
