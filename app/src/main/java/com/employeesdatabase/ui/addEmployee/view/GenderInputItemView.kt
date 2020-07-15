package com.employeesdatabase.ui.addEmployee.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.employeesdatabase.R
import kotlinx.android.synthetic.main.item_gender_input.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class GenderInputItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_gender_input, this)
    }

    @CallbackProp
    fun setOnRadioButtonChanged(onRadioButtonChanged: ((String) -> Unit)?) =
        genderRadioGroup.setOnCheckedChangeListener { radioGroup, buttonId ->
            val buttonText = radioGroup?.findViewById<RadioButton>(buttonId)?.text
            onRadioButtonChanged?.invoke(buttonText.toString())
        }
//    genderRadioGroup.setOnCheckedChangeListener(onRadioButtonChanged)

//
//    @ModelProp
//    fun setHeaderText(@StringRes header: Int?) {
//        headerText.setText(header ?: return)
//    }
}