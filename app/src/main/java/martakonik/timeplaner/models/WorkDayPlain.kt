package martakonik.timeplaner.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class WorkDayPlain(var data: String,
                   var start: String,
                   var end: String) {
    @PrimaryKey (autoGenerate = true)
    var id: Int? = null
}