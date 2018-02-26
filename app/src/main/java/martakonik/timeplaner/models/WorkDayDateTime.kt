package martakonik.timeplaner.models

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit

class WorkDayDateTime(var data: LocalDate, var start: LocalTime, var end: LocalTime) {
    fun convertWorkDayDateTimeToWorkDayPlain(workDayDate : WorkDayDateTime): WorkDayPlain {

        val formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatterHour = DateTimeFormatter.ofPattern("HH:mm")
        return WorkDayPlain(data.format(formatterDate), start.format(formatterHour),
                end.format(formatterHour))
    }

}
