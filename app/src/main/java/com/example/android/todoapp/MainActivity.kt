package com.example.android.todoapp

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.todoapp.database.AppDatabase
import com.example.android.todoapp.databinding.ActivityMainBinding
import com.example.android.todoapp.database.AppDatabaseDao
import com.example.android.todoapp.database.Category
import com.example.android.todoapp.database.Task
import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var drawer:DrawerLayout
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        drawer = binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        val bottomNav= binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.tasksFragment, R.id.tracker))
        setupActionBarWithNavController(navController,appBarConfiguration)
        bottomNav.setupWithNavController(navController)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val dataSource = AppDatabase.getInstance(application).appDatabaseDao
    }
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()

            /*uiScope.launch {
            insertCat(Category(1, "Home", 0), dataSource)
            insertCat(Category(2, "Work", 0), dataSource)
        }*/


            val cal = Calendar.getInstance()

/*
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        val task1 = Task(0, "Shopping", "go to Carrefour", 1, cal.timeInMillis, 0, 1)
        cal.add(Calendar.DAY_OF_MONTH, 3)
        task1.dequeueTime = cal.timeInMillis
        uiScope.launch {
            insert(task1, dataSource)
        }

        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.WEEK_OF_MONTH, 1)
        val task2 = Task(0, "Eating", "go to chicken chester", 2, cal.timeInMillis, 0, 1)
        val task3 = Task(0, "Meeting", "Company meeting", 1, cal.timeInMillis, 0, 2)
        cal.add(Calendar.DAY_OF_MONTH, 2)
        task3.dequeueTime = cal.timeInMillis
        uiScope.launch {
            insert(task2, dataSource)
            insert(task3, dataSource)
        }

        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.WEEK_OF_MONTH, 2)
        val task4 = Task(0, "Work out", "go to GYM", 1, cal.timeInMillis, 0, 1)
        cal.add(Calendar.DAY_OF_MONTH, 2)
        task4.dequeueTime = cal.timeInMillis
        uiScope.launch {
            insert(task4, dataSource)
        }

        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.WEEK_OF_MONTH, 3)
        val task5 = Task(0, "Procrastination", "Just relax", 1, cal.timeInMillis, 0, 1)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        task5.dequeueTime = cal.timeInMillis
        uiScope.launch {
            insert(task5, dataSource)
        }*/

            /*cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.MONTH, 1)
        uiScope.launch {
            //get1(dataSource)
            get(cal.timeInMillis, dataSource)
        }*/

        }}

        override fun onStop() {
            super.onStop()
            viewModelJob.cancel()
        }

        private suspend fun insert(task: Task, database: AppDatabaseDao) {
            withContext(Dispatchers.IO) {
                println(task.categoryId)
                database.insert(task)
            }
        }

        private suspend fun get1(database: AppDatabaseDao) {
            withContext(Dispatchers.IO) {
                println("Offff")
                val values = database.getAllTasks()
                println(values.toString())
                /*for (value in values.value!!.iterator()) {
                println(value)
            }*/
            }
        }

        private suspend fun get(timeCon: Long, database: AppDatabaseDao) {
            withContext(Dispatchers.IO) {
                println("Offff")
                val values = database.getTasksCountInMonthWeeks(timeCon)
                println(values.toString())
                /*for (value in values.value!!.iterator()) {
                println(value)
            }*/
            }
        }

        private suspend fun insertCat(category: Category, database: AppDatabaseDao) {
            withContext(Dispatchers.IO) {
                database.insert(category)
            }
        }

        private suspend fun clear(database: AppDatabaseDao) {
            withContext(Dispatchers.IO) {
                database.clearTasks()
            }
        }
    }
