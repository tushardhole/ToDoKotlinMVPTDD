package com.todo.view.addToDo

import android.widget.EditText

interface AddToDoView {
    fun getMessageView(): EditText?
    fun setResultMessage(resultCode: Int, resultMessage: String, addToDoKey: String)
    fun finishView()
}
