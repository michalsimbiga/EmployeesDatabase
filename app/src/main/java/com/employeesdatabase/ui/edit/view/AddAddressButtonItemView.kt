package com.employeesdatabase.ui.edit.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.employeesdatabase.R
import kotlinx.android.synthetic.main.fragment_edit_add_address_button.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AddAddressButtonItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.fragment_edit_add_address_button, this)
    }

    @CallbackProp
    fun setOnAddAddressCallback(onAddAddressCallback: OnClickListener?) {
        addAddressButton.setOnClickListener(onAddAddressCallback).also { rootView.clearFocus() }
    }
}

