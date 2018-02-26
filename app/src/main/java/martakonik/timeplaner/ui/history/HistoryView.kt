package martakonik.timeplaner.ui.history

import android.widget.EditText

interface HistoryView {
    fun showDatePicker()
    fun setText(format: String?, editText: EditText)
    fun refreshData()
}