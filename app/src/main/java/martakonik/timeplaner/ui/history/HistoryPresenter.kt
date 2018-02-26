package martakonik.timeplaner.ui.history

import android.widget.EditText

interface HistoryPresenter {
    fun onCreated()
    fun onDataSetReceived(year: Int, month: Int, day: Int)
    fun onEditTextFromClick(editTextFrom: EditText)
    fun onEditTextToClick(editTextTo: EditText)
    fun onShowHistoryButtonClick(start: String, end: String)
}