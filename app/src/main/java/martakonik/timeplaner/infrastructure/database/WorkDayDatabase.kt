package martakonik.timeplaner.infrastructure.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import martakonik.timeplaner.domain.models.WorkDayPlain

@Database(entities = arrayOf(WorkDayPlain::class), version = 1)
abstract class WorkDayDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME: String = "WorkDatabase"
        fun create(context: Context): WorkDayDatabase {
            return Room.databaseBuilder(context, WorkDayDatabase::class.java, DB_NAME)
                    .build()
        }
    }

    abstract fun getWorkDayDao(): WorkDayDao
}