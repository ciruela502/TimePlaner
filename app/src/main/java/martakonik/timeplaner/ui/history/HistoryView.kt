package martakonik.timeplaner.ui.history

interface HistoryView {
    fun showDatePicker()
    fun refreshData()
    fun setText(data: String?, editTextId: Int)
}