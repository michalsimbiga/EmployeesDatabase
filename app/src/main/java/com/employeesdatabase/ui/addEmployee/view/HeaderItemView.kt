package com.employeesdatabase.ui.addEmployee.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.employeesdatabase.R
import kotlinx.android.synthetic.main.item_header.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HeaderItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_header, this)
    }

    @ModelProp
    fun setHeaderText(@StringRes header: Int?) {
        headerText.setText(header ?: return)
    }
}