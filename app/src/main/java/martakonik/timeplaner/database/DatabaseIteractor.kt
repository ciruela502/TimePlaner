package martakonik.timeplaner.database

import android.util.Log
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import martakonik.timeplaner.models.WorkDayDateTime
import martakonik.timeplaner.models.WorkDayPlain
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class DatabaseIteractor(var workDayDatabase: WorkDayDatabase) {

    fun getDataForSelectedPeriod(start: String, end: String): Single<List<WorkDayPlain>> {
        val formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val startDate = LocalDate.parse(start, formatterDate)
        val endDate = LocalDate.parse(end, formatterDate)

        return workDayDatabase.getWorkDayDao().loadAllDays()
                .subscribeOn(Schedulers.computation())
                .map { list ->
                    list.filter { dayPlain ->
                        val parse = LocalDate.parse(dayPlain.data, formatterDate)
                        parse.isBefore(endDate) && parse.isAfter(startDate)
                    }
                }
    }


    fun saveWorkDayToDatabase(workDay: WorkDayDateTime) {
        Single.create<Unit> {
            workDayDatabase.getWorkDayDao().insertDay(workDay.convertWorkDayDateTimeToWorkDayPlain(workDay))
            it.onSuccess(Unit)
        }
                .subscribeOn(Schedulers.computation())
                .flatMap { workDayDatabase.getWorkDayDao().loadAllDays() }
                .doOnSuccess {
                    Log.d("raj", "ds")
                }
                .subscribe()

    }

}