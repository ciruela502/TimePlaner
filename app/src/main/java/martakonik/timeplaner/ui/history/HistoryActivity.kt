package martakonik.timeplaner.ui.history

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.DatePicker
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_history.*
import martakonik.timeplaner.GlobalApplication
import martakonik.timeplaner.R
import martakonik.timeplaner.adapter.DayPlainAdapter
import java.util.*

class HistoryActivity : AppCompatActivity(), HistoryView,
        DatePickerDialog.OnDateSetListener {

    private var mCalendar: Calendar = Calendar.getInstance(TimeZone.getDefault())
    private lateinit var mHistoryPresenter: HistoryPresenterImpl
    private lateinit var mDayAdapter: DayPlainAdapter

    override fun showDatePicker() {
        val dialog = DatePickerDialog(this, this,
                mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH))
        dialog.show()
    }

    override fun setText(data: String?, editText: EditText) {
        editText.setText(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val databaseIteractor = (this.application as GlobalApplication).databaseIteractor
        recyclerView.layoutManager = LinearLayoutManager(this)
        mDayAdapter = DayPlainAdapter()
        recyclerView.adapter = mDayAdapter
        mHistoryPresenter = HistoryPresenterImpl(this, databaseIteractor, mDayAdapter)
        mHistoryPresenter.onCreated()

//        val date = LocalDateTime.now()
//        val workDay = WorkDayDateTime(date.toLocalDate(), date.toLocalTime(), date.plusHours(4).toLocalTime())
//
//        var workDayDatabase = (this.application as GlobalApplication).workDayDatabase
//
//        Single.create<Unit> {
//            workDayDatabase.getWorkDayDao().insertDay(workDay.convertWorkDayDateTimeToWorkDayPlain(workDay))
//            it.onSuccess(Unit)
//        }
//                .subscribeOn(Schedulers.computation())
//                .flatMap { workDayDatabase.getWorkDayDao().loadAllDays() }
//                .doOnSuccess {
//                    Log.d("raj", "ds")
//                }
//                .subscribe()

        editTextFrom.setOnClickListener({
            mHistoryPresenter.onEditTextFromClick(editTextFrom)
        })
        editTextTo.setOnClickListener({
            mHistoryPresenter.onEditTextToClick(editTextTo)
        })
        buttonShowHistory.setOnClickListener({
            mHistoryPresenter.onShowHistoryButtonClick(editTextFrom.text.toString(),
                    editTextTo.text.toString())
        })
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        mHistoryPresenter.onDataSetReceived(year, month, day)
    }

    override fun refreshData() {
        mDayAdapter.refresh()
    }
}
