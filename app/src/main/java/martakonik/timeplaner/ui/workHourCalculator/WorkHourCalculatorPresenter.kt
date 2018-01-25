package martakonik.timeplaner.ui.workHourCalculator

import android.widget.EditText

interface WorkHourCalculatorPresenter {
    fun onEditTextFromClick(editTextFrom: EditText)
    fun onEditTextToClick(editTextTo: EditText)
    fun onButtonCountClick(textFrom: String, textTo: String, textPart: String)
    fun onDataSetReceived(year: Int, month: Int, day: Int)

}