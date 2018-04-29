package com.todo.view.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.todo.presenter.ToDoHomePresenter
import com.todo.todokotlinmvp.R
import com.todo.view.addToDo.AddToDoActivity
import kotlin.reflect.KClass

class ToDoHomeActivity : AppCompatActivity(), ToDoHomeView {
    private var homePresenter: ToDoHomePresenter? = null
    private var toDoListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1)
        homePresenter = ToDoHomePresenter(this, listAdapter)
        toDoListView = findViewById(R.id.my_to_do_list)
        homePresenter?.onViewCreated()
    }

    fun addNewTodo(addToDoButton: View) {
        homePresenter?.onAddNewTodo()
    }

    override fun startNewIntent(destination: KClass<AddToDoActivity>, requestCode: Int) {
        val intent = Intent(this, destination.java)
        startActivityForResult(intent, requestCode)
    }

    override fun getToDoListView(): ListView? {
        return toDoListView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        homePresenter?.onActivityResult(requestCode, resultCode, data)
    }
}