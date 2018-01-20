package martakonik.timeplaner.infrastructure

import martakonik.timeplaner.domain.TimeMeasurement

class TimeProvider : TimeMeasurement.TimeProvider {

    override val currentTime: Long
        get() = System.currentTimeMillis()
}