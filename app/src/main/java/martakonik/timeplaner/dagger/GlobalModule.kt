package martakonik.timeplaner.dagger

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import martakonik.timeplaner.database.DatabaseIteractor
import martakonik.timeplaner.database.WorkDayDatabase
import javax.inject.Singleton

@Singleton
@Module
class GlobalModule {

    @Singleton
    @Provides
    fun providesWorkDayDatabase(context: Context): WorkDayDatabase {
        return WorkDayDatabase.create(context)
    }

    @Singleton
    @Provides
    fun providesDatabaseInteractor(database: WorkDayDatabase): DatabaseIteractor {
        return DatabaseIteractor(database)
    }

    @Singleton
    @Provides
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("start", Context.MODE_PRIVATE)
    }
}