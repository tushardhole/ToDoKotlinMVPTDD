package com.todo.view.home

import android.widget.ListView
import com.todo.view.addToDo.AddToDoActivity
import kotlin.reflect.KClass

interface ToDoHomeView {
    fun startNewIntent(destination: KClass<AddToDoActivity>, requestCode: Int)
    fun getToDoListView(): ListView?
}