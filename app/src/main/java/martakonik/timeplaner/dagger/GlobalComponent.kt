package martakonik.timeplaner.dagger

import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import martakonik.timeplaner.GlobalApplication
import martakonik.timeplaner.ui.history.HistoryActivity
import martakonik.timeplaner.ui.timeMeasurement.TimeMeasurementActivity
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        GlobalBinds::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        GlobalModule::class
))
interface GlobalComponent : AndroidInjector<GlobalApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<GlobalApplication>()

}


@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(
            TimeMeasurementModule::class,
            TimeMeasurementPresenterModule::class
    ))
    internal abstract fun contributeTimeMeasurementActivityInjector(): TimeMeasurementActivity

    @ContributesAndroidInjector(modules = arrayOf(
            HistoryPresenterModule::class
    ))
    internal abstract fun contributeHistoryActivityInjector(): HistoryActivity

    @ContributesAndroidInjector(modules = arrayOf(
            WorkHourCalculatorModule::class
    ))
    internal abstract fun contributeyWorkHourCalculatorActivityInjector(): WorkHourCalculatorActivity
}

