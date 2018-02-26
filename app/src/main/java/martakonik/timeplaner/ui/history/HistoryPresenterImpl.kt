package martakonik.timeplaner.ui.history

import android.widget.EditText
import io.reactivex.Single
import martakonik.timeplaner.adapter.AdapterModel
import martakonik.timeplaner.database.DatabaseIteractor
import martakonik.timeplaner.models.WorkDayPlain
import java.text.SimpleDateFormat
import java.util.*

class HistoryPresenterImpl(private var historyPresenter: HistoryView,
                           private var databaseIteractor: DatabaseIteractor,
                           private var adapterModel: AdapterModel) :
        HistoryPresenter {
    override fun onShowHistoryButtonClick(start: String, end: String) {
        var dataForSelectedPeriod: Single<List<WorkDayPlain>> = databaseIteractor.getDataForSelectedPeriod(start, end)
        dataForSelectedPeriod.subscribe({ t ->
            adapterModel.addData(t)
            //            t -> t.forEach{
//            Log.d("MM", it.data)
//        }
        })
        historyPresenter.refreshData()
    }

    override fun onEditTextToClick(editTextTo: EditText) {
        this.editText = editTextTo
        historyPresenter.showDatePicker()
    }

    override fun onEditTextFromClick(editTextFrom: EditText) {
        this.editText = editTextFrom
        historyPresenter.showDatePicker()
    }

    private lateinit var editText: EditText
    private val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.US)

    override fun onDataSetReceived(year: Int, month: Int, day: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val data = sdf.format(calendar.time)
        historyPresenter.setText(data, editText)
    }

    override fun onCreated() {

    }

}