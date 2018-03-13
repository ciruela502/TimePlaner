package martakonik.timeplaner.shared.dagger

import dagger.Binds
import dagger.Module
import martakonik.timeplaner.domain.TimeMeasurement
import martakonik.timeplaner.infrastructure.LocalStorage
import martakonik.timeplaner.infrastructure.TimeProvider

@Module
abstract class TimeMeasurementModule {
    @Binds
    abstract fun watchStorage(a: LocalStorage): TimeMeasurement.WatchStorage

    @Binds
    abstract fun timeProvider(a: TimeProvider): TimeMeasurement.TimeProvider
}
