package martakonik.timeplaner.ui.workHourCalculator

import android.widget.EditText
import martakonik.timeplaner.domain.MyCalendar
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WorkHourCalculatorPresenterImpl @Inject constructor(var interactor: WorkHourCalculatorView) :
        WorkHourCalculatorPresenter {

    private lateinit var editText: EditText
    @Inject
    lateinit var myCalendar: MyCalendar
    private val sdf = SimpleDateFormat("dd/MM/yy", Locale.US)

    override fun onDataSetReceived(year: Int, month: Int, day: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val data = sdf.format(calendar.time)
        interactor.setText(data, editText)
    }

    override fun onEditTextToClick(editTextTo: EditText) {
        this.editText = editTextTo
        interactor.showDatePicker()
    }

    override fun onEditTextFromClick(editTextFrom: EditText) {
        this.editText = editTextFrom
        interactor.showDatePicker()
    }

    override fun onButtonCountClick(textFrom: String, textTo: String, textPart: String) {
        val countWorkHours = myCalendar.countWorkHours(textFrom, textTo, textPart)
        interactor.setWorkHours(countWorkHours)
    }
}