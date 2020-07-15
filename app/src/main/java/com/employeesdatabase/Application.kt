package com.employeesdatabase

import android.app.Application
import com.employeesdatabase.analytics.ActivityAnalytics
import com.employeesdatabase.analytics.ActivityCallbacksImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber

class Application : Application() {

    private val activityLifecycleCallbacks by lazy { ActivityAnalytics(ActivityCallbacksImpl()) }

    override fun onCreate() {
        super.onCreate()

        initKoin()
        setupTimber()
        setupLifecycleCallbacks()
    }

    private fun initKoin() = startKoin {
        androidContext(this@Application)
        androidLogger()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setupLifecycleCallbacks() =
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)

    override fun onTerminate() {
        stopKoin()
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)

        super.onTerminate()
    }

}