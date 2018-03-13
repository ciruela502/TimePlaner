package martakonik.timeplaner.ui.workHourCalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.DatePicker
import android.widget.EditText
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_details.*
import martakonik.timeplaner.R
import martakonik.timeplaner.domain.MyCalendar
import java.util.*
import javax.inject.Inject


class WorkHourCalculatorActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
        WorkHourCalculatorView {

    @Inject
    lateinit var calendar: Calendar
    @Inject
    lateinit var myCalendar: MyCalendar
    @Inject
    lateinit var detailsCalculatorPresenter: WorkHourCalculatorPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        detailsCalculatorPresenter = WorkHourCalculatorPresenterImpl(this, myCalendar)
        editTextFrom.setOnClickListener({
            detailsCalculatorPresenter.onEditTextFromClick(editTextFrom)
        })

        editTextTo.setOnClickListener({
            detailsCalculatorPresenter.onEditTextToClick(editTextTo)
        })

        buttonCount.setOnClickListener({
            detailsCalculatorPresenter.onButtonCountClick(editTextFrom.text.toString(),
                    editTextTo.text.toString(), editTextPartTime.text.toString())
        })
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        detailsCalculatorPresenter.onDataSetReceived(year, month, day)
    }

    override fun setText(data: String?, editText: EditText) {
        editText.setText(data)
    }

    override fun showDatePicker() {
        val dialog = DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
        dialog.show()
    }

    override fun setWorkHours(countWorkHours: Double) {
        textWorkHourCount.text = countWorkHours.toString()
    }
}