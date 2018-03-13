package martakonik.timeplaner.shared.dagger

import dagger.Module
import dagger.Provides
import martakonik.timeplaner.ui.history.adapter.DayPlainAdapter

@Module
@ActivityScope
class AdapterModule {
    @ActivityScope
    @Provides
    fun provideDayPlainAdapter(): DayPlainAdapter {
        return DayPlainAdapter()
    }
}