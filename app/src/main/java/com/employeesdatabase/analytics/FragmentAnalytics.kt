package com.employeesdatabase.analytics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentAnalytics(private val fragmentCallbacks: FragmentCallbacks) :
    FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentCreated(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        savedInstanceState: Bundle?
    ) = fragmentCallbacks.fragmentCreated(fragment)

    override fun onFragmentPaused(fragmentManager: FragmentManager, fragment: Fragment) =
        fragmentCallbacks.fragmentPaused(fragment)

    override fun onFragmentStarted(fragmentManager: FragmentManager, fragment: Fragment) =
        fragmentCallbacks.fragmentStarted(fragment)

    override fun onFragmentDestroyed(fragmentManager: FragmentManager, fragment: Fragment) =
        fragmentCallbacks.fragmentDestroyed(fragment)

    override fun onFragmentStopped(fragmentManager: FragmentManager, fragment: Fragment) =
        fragmentCallbacks.fragmentStopped(fragment)

    override fun onFragmentViewCreated(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        view: View,
        bundle: Bundle?
    ) = fragmentCallbacks.fragmentViewCreated(fragment)

    override fun onFragmentViewDestroyed(fragmentManager: FragmentManager, fragment: Fragment) =
        fragmentCallbacks.fragmentViewDestroyed(fragment)

    override fun onFragmentResumed(fragmentManager: FragmentManager, fragment: Fragment) =
        fragmentCallbacks.fragmentResumed(fragment)
}
