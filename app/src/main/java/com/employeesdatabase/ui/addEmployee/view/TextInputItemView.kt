package com.employeesdatabase.ui.addEmployee.view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.core.widget.doAfterTextChanged
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.employeesdatabase.R
import kotlinx.android.synthetic.main.item_text_input.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TextInputItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.item_text_input, this)
    }

    @CallbackProp
    fun setOnTextChangedCallback(onTextChangedListener: ((String) -> Unit)?) =
        editText.doAfterTextChanged { editable ->
            onTextChangedListener?.invoke(editable.toString())
        }

    @ModelProp
    fun setHint(@StringRes hintText: Int?) {
        textInputLayout.hint = context.getString(hintText ?: return)
    }

    @ModelProp
    fun setIsNumericalInput(isNumerical: Boolean?) {
        editText.inputType =
            if (isNumerical == true) InputType.TYPE_CLASS_NUMBER else InputType.TYPE_TEXT_FLAG_CAP_WORDS
    }

    @OnViewRecycled
    override fun onDetachedFromWindow() {
        editText.addTextChangedListener(null)

        super.onDetachedFromWindow()
    }

}