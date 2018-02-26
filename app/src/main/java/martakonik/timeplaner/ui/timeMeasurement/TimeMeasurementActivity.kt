package martakonik.timeplaner.ui.timeMeasurement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import martakonik.timeplaner.GlobalApplication
import martakonik.timeplaner.R
import martakonik.timeplaner.domain.TimeMeasurement
import martakonik.timeplaner.infrastructure.LocalStorage
import martakonik.timeplaner.infrastructure.TimeProvider
import martakonik.timeplaner.ui.history.HistoryActivity
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorActivity


class TimeMeasurementActivity : AppCompatActivity(), TimeMeasurementView {
    private lateinit var mPresenterImpl: TimeMeasurementActivityPresenterImpl

    companion object {
        private const val SAVED_TIME_PREFERENCES = "start"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val settings = getSharedPreferences(SAVED_TIME_PREFERENCES, Context.MODE_PRIVATE)
        var databaseIteractor = (this.application as GlobalApplication).databaseIteractor
        mPresenterImpl = TimeMeasurementActivityPresenterImpl(this,
                TimeMeasurement(LocalStorage(settings), TimeProvider()), databaseIteractor)
        mPresenterImpl.onActivityCreate()

        btnStartWorking.setOnClickListener {
            mPresenterImpl.onStartWorkingButtonClick()
        }

        btnFinishWorking.setOnClickListener {
            mPresenterImpl.onFinishWorkingButtonClick()
        }

        btnDetails.setOnClickListener {
            startActivity(Intent(this, WorkHourCalculatorActivity::class.java))
        }

        btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }

    override fun onUpdateTimeMessage(elapsedTime: String) {
        tvTime.text = elapsedTime
    }

    override fun onStop() {
        mPresenterImpl.onStop()
        super.onStop()
    }
}