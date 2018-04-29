package com.todo.presenter

import android.content.Intent
import android.widget.ArrayAdapter
import com.todo.constants.ADD_TO_DO_KEY
import com.todo.constants.ADD_TO_DO_REQUEST_CODE
import com.todo.constants.ADD_TO_DO_RESULT_CODE
import com.todo.view.addToDo.AddToDoActivity
import com.todo.view.home.ToDoHomeView

class ToDoHomePresenter(val view: ToDoHomeView, val toDoListAdapter: ArrayAdapter<String>) {

    fun onViewCreated() {
        view.getToDoListView()?.adapter = this.toDoListAdapter
    }

    fun onAddNewTodo() {
        view.startNewIntent(destination = AddToDoActivity::class, requestCode = ADD_TO_DO_REQUEST_CODE)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_TO_DO_REQUEST_CODE && resultCode == ADD_TO_DO_RESULT_CODE && data != null) {
            this.toDoListAdapter.add(data.getStringExtra(ADD_TO_DO_KEY))
            this.toDoListAdapter.notifyDataSetChanged()
        }
    }
}
