package com.todo.presenter

import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.ListView
import com.nhaarman.mockito_kotlin.*
import com.todo.constants.ADD_TO_DO_KEY
import com.todo.constants.ADD_TO_DO_REQUEST_CODE
import com.todo.constants.ADD_TO_DO_RESULT_CODE
import com.todo.view.addToDo.AddToDoActivity
import com.todo.view.home.ToDoHomeView
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.reflect.KClass


class ToDoHomePresenterTest {

    val mockHomeView = mock<ToDoHomeView>()
    var homePresenter: ToDoHomePresenter? = null
    val mockAdapter = mock<ArrayAdapter<String>>()

    @Before
    fun setUp() {
        homePresenter = ToDoHomePresenter(mockHomeView, mockAdapter)
    }

    @Test
    fun shouldStartNewToDoActivityOnAddNewToDoEvent() {
        homePresenter?.onAddNewTodo()
        var destinationCapture = argumentCaptor<KClass<AddToDoActivity>>()
        var requestCodeCapture = argumentCaptor<Int>()

        verify(mockHomeView).startNewIntent(destinationCapture.capture(), requestCodeCapture.capture())
        Assert.assertEquals(AddToDoActivity::class.java, destinationCapture.firstValue.java)
        Assert.assertEquals(1111, requestCodeCapture.firstValue)
    }

    @Test
    fun shouldDisplayToDoListOnViewCreatedEvent() {
        val mockListView = mock<ListView>()
        whenever(mockHomeView.getToDoListView()).thenReturn(mockListView)

        homePresenter?.onViewCreated()
        verify(mockListView).setAdapter(any<ArrayAdapter<String>>())
    }

    @Test
    fun shouldDisplayUpdatedToDoListOnResultFromAddToDoView() {
        val mockListView = mock<ListView>()
        whenever(mockHomeView.getToDoListView()).thenReturn(mockListView)
        whenever(mockListView.context).thenReturn(mock())

        var newToDoData = mock<Intent>()
        whenever(newToDoData.getStringExtra(eq(ADD_TO_DO_KEY))).thenReturn("Need to pay phone bill!!")

        homePresenter?.onActivityResult(ADD_TO_DO_REQUEST_CODE, ADD_TO_DO_RESULT_CODE, newToDoData)

        var newMessage = argumentCaptor<String>()

        verify(mockAdapter).add(newMessage.capture())
        verify(mockAdapter).notifyDataSetChanged()

        Assert.assertEquals("Need to pay phone bill!!", newMessage.firstValue)
    }
}

