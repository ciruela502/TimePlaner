package martakonik.timeplaner.shared.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import martakonik.timeplaner.ui.history.HistoryActivity
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementActivity
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorActivity

@Module
@ActivityScope
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(
            TimeMeasurementModule::class,
            TimeMeasurementPresenterModule::class
    ))
    internal abstract fun contributeTimeMeasurementActivityInjector(): TimeMeasurementActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(
            HistoryPresenterModule::class,
            CalendarModule::class,
            AdapterModule::class
    ))
    internal abstract fun contributeHistoryActivityInjector(): HistoryActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(
            WorkHourCalculatorModule::class,
            CalendarModule::class
    ))
    internal abstract fun contributeyWorkHourCalculatorActivityInjector(): WorkHourCalculatorActivity
}