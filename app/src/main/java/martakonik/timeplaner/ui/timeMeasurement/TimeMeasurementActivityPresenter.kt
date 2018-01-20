package martakonik.timeplaner.ui.timeMeasurement

interface TimeMeasurementActivityPresenter {
    fun onActivityCreate()
    fun onStop()
    fun onStartWorkingButtonClick()
    fun onFinishWorkingButtonClick()
    fun onDetailsButtonClick()
}