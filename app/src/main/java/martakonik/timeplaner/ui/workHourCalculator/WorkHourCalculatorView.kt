package martakonik.timeplaner.ui.workHourCalculator

import android.widget.EditText

interface WorkHourCalculatorView {
    fun showDatePicker()
    fun setText(format: String?, editText: EditText)
    fun setWorkHours(countWorkHours: Double)

}