package martakonik.timeplaner.infrastructure

import martakonik.timeplaner.domain.TimeMeasurement
import javax.inject.Inject

class TimeProvider @Inject constructor() : TimeMeasurement.TimeProvider {

    override val currentTime: Long
        get() = System.currentTimeMillis()
}