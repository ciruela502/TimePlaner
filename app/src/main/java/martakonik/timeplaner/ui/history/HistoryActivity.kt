package martakonik.timeplaner.ui.history

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.DatePicker
import android.widget.EditText
import com.jakewharton.rxbinding2.view.clicks
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_history.*
import martakonik.timeplaner.R
import martakonik.timeplaner.shared.disposeWith
import martakonik.timeplaner.ui.history.adapter.AdapterView
import martakonik.timeplaner.ui.history.adapter.DayPlainAdapter
import java.util.*
import javax.inject.Inject

class HistoryActivity : AppCompatActivity(), HistoryView,
        DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var mCalendar: Calendar
    @Inject
    lateinit var mHistoryPresenter: HistoryPresenterImpl
    @Inject
    lateinit var mDayAdapter: DayPlainAdapter
    @Inject
    lateinit var mAdapterView: AdapterView

    override fun showDatePicker() {
        val dialog = DatePickerDialog(this, this,
                mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH))
        dialog.show()
    }

    var disposeBag = CompositeDisposable()

    override fun setText(data: String?, editTextId: Int) {
        findViewById<EditText>(editTextId).setText(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mDayAdapter

        editTextFrom.setOnClickListener({
            mHistoryPresenter.onEditTextClick(editTextFrom.id)
        })
        editTextTo.setOnClickListener({
            mHistoryPresenter.onEditTextClick(editTextTo.id)
        })

        buttonShowHistory.clicks()
                .flatMapCompletable { mHistoryPresenter.onShowHistoryButtonClick(editTextFrom.text.toString(), editTextTo.text.toString()) }
                .subscribe()
                .disposeWith(disposeBag)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        mHistoryPresenter.onDataSetReceived(year, month, day)
    }

    override fun refreshData() {
        mAdapterView.refresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.dispose()
    }
}

