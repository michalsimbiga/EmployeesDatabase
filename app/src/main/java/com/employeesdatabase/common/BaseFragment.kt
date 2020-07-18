package com.employeesdatabase.common

import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import com.employeesdatabase.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : BaseMvRxFragment() {

    fun onRecyclerViewDetached(view: RecyclerView) {
        view.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(v: View?) {
                view.adapter = null
            }

            override fun onViewAttachedToWindow(v: View?) =
                doNothing
        })
    }

    fun showSnackbar(@StringRes messageRes: Int, isShort: Boolean = true) =
        (requireActivity() as MainActivity).showSnackbar(messageRes, isShort)
}