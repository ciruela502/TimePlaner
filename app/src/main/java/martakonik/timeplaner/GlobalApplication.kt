package martakonik.timeplaner

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import martakonik.timeplaner.shared.dagger.DaggerGlobalComponent
import javax.inject.Inject


class GlobalApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerGlobalComponent.builder().create(this).inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}
