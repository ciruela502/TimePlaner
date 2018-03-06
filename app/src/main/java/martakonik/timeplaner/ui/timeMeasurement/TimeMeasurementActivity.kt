package martakonik.timeplaner.ui.timeMeasurement

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import martakonik.timeplaner.R
import martakonik.timeplaner.ui.history.HistoryActivity
import martakonik.timeplaner.ui.workHourCalculator.WorkHourCalculatorActivity
import javax.inject.Inject


class TimeMeasurementActivity : AppCompatActivity(), TimeMeasurementView {
    @Inject
    lateinit var mPresenterImpl: TimeMeasurementActivityPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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