package martakonik.timeplaner.ui.timeMeasurement

import io.reactivex.Completable

interface TimeMeasurementActivityPresenter {
    fun onActivityCreate()
    fun onStop()
    fun onStartWorkingButtonClick()
    fun onFinishWorkingButtonClick(): Completable
}