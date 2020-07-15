package com.employeesdatabase.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import com.employeesdatabase.doNothing

abstract class BaseFragment : BaseMvRxFragment() {

    fun onRecyclerViewDetached(view: RecyclerView) {
        view.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(v: View?) {
                view.adapter = null
            }

            override fun onViewAttachedToWindow(v: View?) = doNothing
        })
    }
}