package martakonik.timeplaner.dagger

import android.content.Context
import dagger.Binds
import dagger.Module
import martakonik.timeplaner.GlobalApplication

@Module
interface GlobalBinds {

    @Binds
    fun bind(application: GlobalApplication): Context
}