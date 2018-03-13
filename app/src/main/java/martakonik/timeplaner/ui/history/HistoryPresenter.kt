package martakonik.timeplaner.ui.history

import io.reactivex.Completable

interface HistoryPresenter {
    fun onDataSetReceived(year: Int, month: Int, day: Int)
    fun onEditTextClick(editTextFrom: Int)
    fun onShowHistoryButtonClick(start: String, end: String): Completable
}