package martakonik.timeplaner.infrastructure

import android.content.SharedPreferences
import martakonik.timeplaner.domain.TimeMeasurement
import javax.inject.Inject

class LocalStorage @Inject constructor(
        private val sharedPreferences: SharedPreferences
) : TimeMeasurement.WatchStorage {

    companion object {
        private const val START_TIME = "startTime"
        private const val RUN = "run"
    }

    override fun get(): TimeMeasurement.State {
        val startTime = sharedPreferences.getLong(START_TIME, 0)
        val running = sharedPreferences.getBoolean(RUN, false)
        return TimeMeasurement.State(startTime, running)
    }

    override fun save(state: TimeMeasurement.State) {
        val editor = sharedPreferences.edit()
        editor.putLong(START_TIME, state.startTime)
        editor.putBoolean(RUN, state.running)
        editor.apply()
    }
}