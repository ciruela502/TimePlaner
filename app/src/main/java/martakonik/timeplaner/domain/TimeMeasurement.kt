package martakonik.timeplaner.domain

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TimeMeasurement @Inject constructor(
        private val watchStorage: WatchStorage,
        private val timeProvider: TimeProvider
) {
    var mRunning: Boolean = false
    var mStartTime: Long = 0
    lateinit var mStartDateTime: LocalTime
    lateinit var mFinishDateTime: LocalTime
    var mWorkDate: LocalDate

    init {
        val state = watchStorage.get()
        mWorkDate = LocalDate.now()
        if (state.running) {
            mStartTime = state.startTime
            mRunning = true
        } else {
            mStartTime = timeProvider.currentTime
            mRunning = false
            mStartDateTime = LocalTime.now()
        }
    }

    fun start() {
        if (!mRunning) {
            mStartTime = timeProvider.currentTime
        }
        mRunning = true
    }

    fun elapsedTime(): String {
        val milliseconds = timeProvider.currentTime - mStartTime
        return miliToTimeMin(milliseconds)
    }

    private fun miliToTimeMin(milliseconds: Long): String {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliseconds),
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
                        TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))
        )
    }

    fun finish() {
        mStartTime = timeProvider.currentTime
        mRunning = false
        mFinishDateTime = LocalTime.now()
    }

    fun stop() {
        watchStorage.save(State(mStartTime, mRunning))
    }

    data class State(val startTime: Long, val running: Boolean)

    interface WatchStorage {
        fun save(state: State)

        fun get(): State
    }

    interface TimeProvider {
        val currentTime: Long
    }
}
