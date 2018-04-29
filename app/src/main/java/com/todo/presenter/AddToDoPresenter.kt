package com.todo.presenter

import com.todo.constants.ADD_TO_DO_KEY
import com.todo.constants.ADD_TO_DO_RESULT_CODE
import com.todo.view.addToDo.AddToDoView

class AddToDoPresenter(val view: AddToDoView) {

    fun onSaveToDo() {

        view.getMessageView()?.text.let {
            if(it.toString().isNotBlank()) {
                view.setResultMessage(ADD_TO_DO_RESULT_CODE, view.getMessageView()?.text.toString(), ADD_TO_DO_KEY)
                view.finishView()
            }
        }
    }
}