package com.example.android.todoapp.recyclerView

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.android.todoapp.database.AppDatabaseDao
import com.example.android.todoapp.database.Task


@BindingAdapter("description")
fun TextView.setDescription(item : Task?){
    item?.let {
        text=item.description
    }
}
@BindingAdapter("Title")
fun TextView.setTitle(item : Task?){
    item?.let {
        text=item.title
    }
}
