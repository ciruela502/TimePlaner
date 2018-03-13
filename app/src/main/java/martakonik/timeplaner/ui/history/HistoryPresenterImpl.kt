package martakonik.timeplaner.ui.history

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import martakonik.timeplaner.infrastructure.database.DatabaseIteractor
import martakonik.timeplaner.ui.history.adapter.AdapterModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class HistoryPresenterImpl
@Inject constructor(
        private var historyPresenter: HistoryView,
        private var databaseIteractor: DatabaseIteractor,
        private var adapterModel: AdapterModel
) : HistoryPresenter {

    private var mEditTextId: Int = 0
    private val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.US)

    override fun onShowHistoryButtonClick(start: String, end: String): Completable {
        return databaseIteractor.getDataForSelectedPeriod(start, end)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    adapterModel.addData(it)
                    historyPresenter.refreshData()
                }.toCompletable()
    }

    override fun onEditTextClick(mEditTextId: Int) {
        this.mEditTextId = mEditTextId
        historyPresenter.showDatePicker()
    }

    override fun onDataSetReceived(year: Int, month: Int, day: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val data = sdf.format(calendar.time)
        historyPresenter.setText(data, mEditTextId)
    }

}