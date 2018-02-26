package martakonik.timeplaner.adapter

import martakonik.timeplaner.models.WorkDayPlain

interface AdapterModel {
    fun addData(dayPlains: List<WorkDayPlain>)
}
