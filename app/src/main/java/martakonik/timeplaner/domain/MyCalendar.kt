package martakonik.timeplaner.domain

import android.util.Log
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject


class MyCalendar @Inject constructor() {

    fun countWorkHours(from: String, to: String, part: String): Double {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yy")
        val fromDate: LocalDate = LocalDate.parse(from, formatter)
        val toDate: LocalDate = LocalDate.parse(to, formatter)
        val numbers = part.split("/")
        val numerator = numbers[0].toDouble()
        val denominator = numbers[1].toDouble()
        val partLong: Double = (numerator / denominator)

        var startDate = fromDate
        var workDay: Long = 0

        while (toDate.plusDays(1).isAfter(startDate)) {
            Log.d("dni", "" + startDate)
            if (!isHoliday(startDate)) {
                workDay++
            }
            startDate = startDate.plusDays(1)
        }

        return workDay * 8 * partLong
    }

    private fun isHoliday(day: LocalDate): Boolean {
        when {
            day.dayOfWeek == DayOfWeek.SUNDAY -> return true
            day.dayOfWeek == DayOfWeek.SATURDAY -> return true
            day.dayOfMonth == 1 && day.month == Month.JANUARY -> return true // Nowy Rok
            day.dayOfMonth == 6 && day.month == Month.JANUARY -> return true // 3 kroli
            day.month == Month.MAY && day.dayOfMonth == 1 -> return true // 1 maja
            day.month == Month.MAY && day.dayOfMonth == 3 -> return true // 3 maja
            day.month == Month.AUGUST && day.dayOfMonth == 15 -> return true // Wniebowzięcie Najświętszej Marii Panny, Święto Wojska Polskiego
            day.month == Month.NOVEMBER && day.dayOfMonth == 1 -> return true // Dzień Wszystkich Świętych
            day.month == Month.NOVEMBER && day.dayOfMonth == 11 -> return true // Dzień Niepodległości
            day.month == Month.DECEMBER && day.dayOfMonth == 25 -> return true // Boże Narodzenie
            day.month == Month.DECEMBER && day.dayOfMonth == 26 -> return true // Boże Narodzenie
        // Wielkanoc (poniedziałek)
        // Boże Ciało
            else -> {
                val a = day.year % 19
                val b = day.year % 4
                val c = day.year % 7
                var d = (a * 19 + 24) % 30
                val e = (2 * b + 4 * c + 6 * d + 5) % 7
                if (d == 29 && e == 6) d -= 7
                if (d == 28 && e == 6 && a > 10) d -= 7
                val date: LocalDate = LocalDate.of(day.year, 3, 22)
                val easter = date.plusDays((d + e).toLong())
                if (day.plusDays((-1).toLong()) == easter)
                    return true // Wielkanoc (poniedziałek)
                return day.plusDays(-60) == easter // Boże Ciało
            }
        }
    }
}
