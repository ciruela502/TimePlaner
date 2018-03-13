package martakonik.timeplaner.shared.dagger

import dagger.Binds
import dagger.Module
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorActivity
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorPresenter
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorPresenterImpl
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorView

@Module
abstract class WorkHourCalculatorModule {

    @Binds
    abstract fun workHourCalculatorPresenter(a: WorkHourCalculatorPresenterImpl): WorkHourCalculatorPresenter

    @Binds
    abstract fun workHourCalculatorView(a: WorkHourCalculatorActivity): WorkHourCalculatorView
}