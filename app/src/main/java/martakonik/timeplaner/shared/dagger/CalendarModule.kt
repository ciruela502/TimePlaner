package martakonik.timeplaner.shared.dagger

import dagger.Module
import dagger.Provides
import java.util.*

@Module
class CalendarModule {

    @Provides
    fun provideCalendar(): Calendar {
        return Calendar.getInstance(TimeZone.getDefault())
    }
}