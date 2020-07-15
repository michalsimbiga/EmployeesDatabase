package com.employeesdatabase.analytics

import androidx.fragment.app.Fragment

interface FragmentCallbacks {
    fun fragmentCreated(fragment: Fragment)
    fun fragmentViewCreated(fragment: Fragment)
    fun fragmentResumed(fragment: Fragment)
    fun fragmentPaused(fragment: Fragment)
    fun fragmentDestroyed(fragment: Fragment)
    fun fragmentViewDestroyed(fragment: Fragment)
    fun fragmentStarted(fragment: Fragment)
    fun fragmentStopped(fragment: Fragment)
}
