package martakonik.timeplaner.ui.history

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.DatePicker
import android.widget.EditText
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_history.*
import martakonik.timeplaner.GlobalApplication
import martakonik.timeplaner.R
import martakonik.timeplaner.adapter.DayPlainAdapter
import martakonik.timeplaner.database.DatabaseIteractor
import java.util.*
import javax.inject.Inject

class HistoryActivity : AppCompatActivity(), HistoryView,
        DatePickerDialog.OnDateSetListener {

    private var mCalendar: Calendar = Calendar.getInstance(TimeZone.getDefault())
    @Inject
    lateinit var mHistoryPresenter: HistoryPresenterImpl
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
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mDayAdapter = DayPlainAdapter()
        recyclerView.adapter = mDayAdapter
        mHistoryPresenter.onCreated()

//        val date = LocalDateTime.now()
//        val workDay = WorkDayDateTime(date.toLocalDate(), date.toLocalTime(), date.plusHours(4).toLocalTime())
//
//        var providesWorkDayDatabase = (this.application as GlobalApplication).providesWorkDayDatabase
//
//        Single.create<Unit> {
//            providesWorkDayDatabase.getWorkDayDao().insertDay(workDay.convertWorkDayDateTimeToWorkDayPlain(workDay))
//            it.onSuccess(Unit)
//        }
//                .subscribeOn(Schedulers.computation())
//                .flatMap { providesWorkDayDatabase.getWorkDayDao().loadAllDays() }
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
