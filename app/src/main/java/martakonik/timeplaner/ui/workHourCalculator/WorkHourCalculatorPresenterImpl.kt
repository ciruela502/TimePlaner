package martakonik.timeplaner.ui.workHourCalculator

import android.widget.EditText
import martakonik.timeplaner.domain.MyCalendar
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WorkHourCalculatorPresenterImpl
@Inject constructor(
        var view: WorkHourCalculatorView,
        var myCalendar: MyCalendar
) : WorkHourCalculatorPresenter {

    private lateinit var editText: EditText
    private val sdf = SimpleDateFormat("dd/MM/yy", Locale.US)

    override fun onDataSetReceived(year: Int, month: Int, day: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val data = sdf.format(calendar.time)
        view.setText(data, editText)
    }

    override fun onEditTextToClick(editTextTo: EditText) {
        this.editText = editTextTo
        view.showDatePicker()
    }

    override fun onEditTextFromClick(editTextFrom: EditText) {
        this.editText = editTextFrom
        view.showDatePicker()
    }

    override fun onButtonCountClick(textFrom: String, textTo: String, textPart: String) {
        val countWorkHours = myCalendar.countWorkHours(textFrom, textTo, textPart)
        view.setWorkHours(countWorkHours)
    }
}