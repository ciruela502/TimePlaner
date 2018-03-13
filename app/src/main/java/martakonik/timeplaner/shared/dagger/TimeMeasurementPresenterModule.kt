package martakonik.timeplaner.shared.dagger

import dagger.Binds
import dagger.Module
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementActivity
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementActivityPresenter
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementActivityPresenterImpl
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementView

@Module
abstract class TimeMeasurementPresenterModule {

    @Binds
    abstract fun timeMeasurementPresenter(a: TimeMeasurementActivityPresenterImpl): TimeMeasurementActivityPresenter

    @Binds
    abstract fun timeMeasurementView(a: TimeMeasurementActivity): TimeMeasurementView
}