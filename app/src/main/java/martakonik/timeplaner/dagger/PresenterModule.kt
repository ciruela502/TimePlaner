package martakonik.timeplaner.dagger

import dagger.Binds
import dagger.Module
import martakonik.timeplaner.adapter.AdapterModel
import martakonik.timeplaner.adapter.DayPlainAdapter
import martakonik.timeplaner.ui.history.HistoryActivity
import martakonik.timeplaner.ui.history.HistoryPresenter
import martakonik.timeplaner.ui.history.HistoryPresenterImpl
import martakonik.timeplaner.ui.history.HistoryView
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementActivity
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementActivityPresenter
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementActivityPresenterImpl
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementView
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorActivity
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorPresenter
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorPresenterImpl
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorView

@Module
abstract class TimeMeasurementPresenterModule {

    @Binds
    abstract fun timeMeasurementPresenter(a: TimeMeasurementActivityPresenterImpl): TimeMeasurementActivityPresenter

    @Binds
    abstract fun timeMeasurementView(a: TimeMeasurementActivity): TimeMeasurementView
}

@Module
abstract class HistoryPresenterModule {

    @Binds
    abstract fun historyPresenter(a: HistoryPresenterImpl): HistoryPresenter

    @Binds
    abstract fun historyView(a: HistoryActivity): HistoryView

    @Binds
    abstract fun adapterModel(a: DayPlainAdapter): AdapterModel
}

@Module
abstract class WorkHourCalculatorModule {

    @Binds
    abstract fun workHourCalculatorPresenter(a: WorkHourCalculatorPresenterImpl): WorkHourCalculatorPresenter

    @Binds
    abstract fun workHourCalculatorView(a: WorkHourCalculatorActivity): WorkHourCalculatorView
}