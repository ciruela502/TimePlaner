package martakonik.timeplaner

import android.app.Application
import martakonik.timeplaner.database.DatabaseIteractor
import martakonik.timeplaner.database.WorkDayDatabase

class GlobalApplication : Application() {

    lateinit var workDayDatabase: WorkDayDatabase
    lateinit var databaseIteractor: DatabaseIteractor

    override fun onCreate() {
        super.onCreate()
        workDayDatabase = WorkDayDatabase.create(this)
        databaseIteractor = DatabaseIteractor(workDayDatabase);
    }
}
