package martakonik.timeplaner.shared.dagger

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

