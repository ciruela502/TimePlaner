package martakonik.timeplaner.infrastructure.database

import android.arch.persistence.room.*
import io.reactivex.Single
import martakonik.timeplaner.domain.models.WorkDayPlain


@Dao
interface WorkDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDay(day: WorkDayPlain)


    @Query("SELECT * FROM WorkDayPlain")
    fun loadAllDays(): Single<List<WorkDayPlain>>

    @Update
    fun update(workDay: WorkDayPlain)


}