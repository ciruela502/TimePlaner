package martakonik.timeplaner.ui.timeMeasurement

import android.os.Handler
import android.os.Message
import io.reactivex.Completable
import martakonik.timeplaner.domain.TimeMeasurement
import martakonik.timeplaner.domain.models.WorkDayDateTime
import martakonik.timeplaner.infrastructure.database.DatabaseIteractor
import javax.inject.Inject

class TimeMeasurementActivityPresenterImpl
@Inject constructor(
        private var mTimeMeasurementPresenterView: TimeMeasurementView,
        private val mTimeMeasurement: TimeMeasurement,
        private val databaseIteractor: DatabaseIteractor
) : TimeMeasurementActivityPresenter {

    private lateinit var mHandler: WatchHandler

    override fun onActivityCreate() {
        mHandler = WatchHandler()
        if (mTimeMeasurement.mRunning) {
            sendMessageStart()
        }
    }

    override fun onStartWorkingButtonClick() {
        sendMessageStart()
    }

    override fun onFinishWorkingButtonClick(): Completable {
        sendMessageFinish()
        var workDayDateTime = WorkDayDateTime(mTimeMeasurement.mWorkDate,
                mTimeMeasurement.mStartDateTime, mTimeMeasurement.mFinishDateTime)
        return databaseIteractor.saveWorkDayToDatabase(workDayDateTime)
    }

    override fun onStop() {
        mTimeMeasurement.stop()
    }

    companion object {
        internal const val MSG_START_TIMER = 0
        internal const val MSG_STOP_TIMER = 1
        internal const val MSG_UPDATE_TIMER = 2
    }

    inner class WatchHandler : Handler() {
        private val REFRESH_RATE: Long = 1000
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when (msg.what) {
                MSG_START_TIMER -> this.sendEmptyMessage(MSG_UPDATE_TIMER)

                MSG_UPDATE_TIMER -> {
                    this.sendEmptyMessageDelayed(MSG_UPDATE_TIMER, REFRESH_RATE)
                    mTimeMeasurementPresenterView.onUpdateTimeMessage(mTimeMeasurement.elapsedTime())
                }

                MSG_STOP_TIMER -> this.removeMessages(MSG_UPDATE_TIMER)
            }
        }
    }

    private fun sendMessageStart() {
        mTimeMeasurement.start()
        mHandler.sendEmptyMessage(MSG_START_TIMER)
    }

    private fun sendMessageFinish() {
        mTimeMeasurement.finish()
        mHandler.sendEmptyMessage(MSG_STOP_TIMER)
    }
}
