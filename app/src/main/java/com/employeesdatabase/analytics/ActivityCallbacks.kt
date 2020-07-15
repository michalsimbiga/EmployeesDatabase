package com.employeesdatabase.analytics

import android.app.Activity

interface ActivityCallbacks {
    fun activityCreated(activity: Activity)
    fun activityResumed(activity: Activity)
    fun activityStarted(activity: Activity)
    fun activityPaused(activity: Activity)
    fun activityStopped(activity: Activity)
    fun activitySaveInstanceState(activity: Activity)
    fun activityDestroyed(activity: Activity)
}
