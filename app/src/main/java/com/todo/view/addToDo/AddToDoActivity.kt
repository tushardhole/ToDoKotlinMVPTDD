package com.todo.view.addToDo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import com.todo.presenter.AddToDoPresenter
import com.todo.todokotlinmvp.R

class AddToDoActivity : AppCompatActivity(), AddToDoView {

    private var message: EditText? = null
    private var presenter: AddToDoPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_to_do_layout);
        message = findViewById(R.id.new_msg);
        presenter = AddToDoPresenter(this)
    }

    fun onSave(view: View) {
        presenter?.onSaveToDo()
    }

    override fun getMessageView(): EditText? {
        return message
    }

    override fun setResultMessage(resultCode: Int, resultMessage: String, addToDoKey: String) {
        val out = Intent()
        out.putExtra(addToDoKey, resultMessage)
        setResult(resultCode, out)
    }
    override fun finishView() {
        finish()
    }
}