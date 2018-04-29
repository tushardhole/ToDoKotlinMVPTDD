package com.todo.presenter

import android.text.Editable
import android.widget.EditText
import com.nhaarman.mockito_kotlin.*
import com.todo.constants.ADD_TO_DO_KEY
import com.todo.constants.ADD_TO_DO_RESULT_CODE
import com.todo.view.addToDo.AddToDoView
import org.junit.Test

class AddToDoPresenterTest {

    private val mockView = mock<AddToDoView>()
    private val presenter = AddToDoPresenter(mockView)

    @Test
    fun onSaveToDoShouldNavigateToHomeWithNewToDoMsg() {
        val mockMessageView = mock<EditText>()
        val mockEditable = mock<Editable>()

        whenever(mockEditable.toString()).thenReturn("Need to do hair cut!!")
        whenever(mockMessageView.text).thenReturn(mockEditable)
        whenever(mockView.getMessageView()).thenReturn(mockMessageView)

        presenter.onSaveToDo()

        verify(mockView).setResultMessage(eq(ADD_TO_DO_RESULT_CODE), eq("Need to do hair cut!!"), eq(ADD_TO_DO_KEY))
        verify(mockView).finishView()
    }

    @Test
    fun onSaveToDoShouldNotNavigateToHomeWithNewToDoMsg() {
        val mockMessageView = mock<EditText>()
        val mockEditable = mock<Editable>()

        whenever(mockEditable.toString()).thenReturn("")
        whenever(mockMessageView.text).thenReturn(mockEditable)
        whenever(mockView.getMessageView()).thenReturn(mockMessageView)

        presenter.onSaveToDo()

        verify(mockView, never()).setResultMessage(any(), any(), any())
        verify(mockView, never()).finishView()
    }
}