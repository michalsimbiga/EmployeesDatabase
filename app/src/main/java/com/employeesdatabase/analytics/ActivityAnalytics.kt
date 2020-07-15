package com.employeesdatabase.analytics

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityAnalytics(private val activityCallbacks: ActivityCallbacks) :
    Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) =
        activityCallbacks.activityCreated(activity)

    override fun onActivityPaused(activity: Activity) = activityCallbacks.activityPaused(activity)

    override fun onActivityStarted(activity: Activity) = activityCallbacks.activityStarted(activity)

    override fun onActivityDestroyed(activity: Activity) = activityCallbacks.activityDestroyed(activity)

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) =
        activityCallbacks.activitySaveInstanceState(activity)

    override fun onActivityStopped(activity: Activity) = activityCallbacks.activityStopped(activity)

    override fun onActivityResumed(activity: Activity) = activityCallbacks.activityResumed(activity)
}
