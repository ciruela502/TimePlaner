package martakonik.timeplaner.ui.history.adapter

import martakonik.timeplaner.domain.models.WorkDayPlain

interface AdapterModel {
    fun addData(dayPlains: List<WorkDayPlain>)
}
