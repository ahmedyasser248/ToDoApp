package com.example.android.todoapp.addtasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.todoapp.R
import kotlinx.android.synthetic.main.addtaskfragent.view.*

class AddTaskFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : AddtaskfragmentBinding=DataBindingUtil.inflate(inflater,R.layout.addtaskfragent,container,false)


    }
}