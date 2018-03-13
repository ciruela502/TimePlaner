package martakonik.timeplaner.shared.dagger

import dagger.Binds
import dagger.Module
import martakonik.timeplaner.ui.history.HistoryActivity
import martakonik.timeplaner.ui.history.HistoryPresenter
import martakonik.timeplaner.ui.history.HistoryPresenterImpl
import martakonik.timeplaner.ui.history.HistoryView
import martakonik.timeplaner.ui.history.adapter.AdapterModel
import martakonik.timeplaner.ui.history.adapter.AdapterView
import martakonik.timeplaner.ui.history.adapter.DayPlainAdapter

@Module
@ActivityScope
abstract class HistoryPresenterModule {

    @Binds
    abstract fun historyPresenter(a: HistoryPresenterImpl): HistoryPresenter

    @Binds
    abstract fun historyView(a: HistoryActivity): HistoryView

    @Binds
    @ActivityScope
    abstract fun adapterModel(a: DayPlainAdapter): AdapterModel

    @Binds
    @ActivityScope
    abstract fun adapterView(a: DayPlainAdapter): AdapterView
}
