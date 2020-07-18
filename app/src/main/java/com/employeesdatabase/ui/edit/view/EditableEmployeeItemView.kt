package com.employeesdatabase.ui.edit.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.RadioButton
import androidx.core.widget.doAfterTextChanged
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.employeesdatabase.R
import com.employeesdatabase.extensions.onNotBlankCallback
import com.employeesdatabase.models.EmployeeItem
import kotlinx.android.synthetic.main.fragment_edit_editable_employee_item.view.*

typealias stringCallback = ((String) -> Unit)?

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EditableEmployeeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.fragment_edit_editable_employee_item, this)
    }

    @ModelProp
    fun setEmployeeModel(employee: EmployeeItem?) {
        editableEmployeeFirstNameTextEdit.setText(employee?.firstName)
        editableEmployeeLastNameTextEdit.setText(employee?.lastName)
        employee?.age?.let { age ->
            if (age in AGE_RANGE) editableEmployeeAgeTextEdit.setText(age.toString())
        }

        with(context) {
            val buttonId = when (employee?.gender) {
                getString(R.string.gender_male) -> R.id.editableEmployeeMaleRadioButton
                getString(R.string.gender_female) -> R.id.editableEmployeeFemaleRadioButton
                getString(R.string.gender_not_disclosed) -> R.id.editableEmployeeNotDisclosedRadioButton
                else -> return@with
            }
            editableEmployeeGenderRadioGroup.check(buttonId)
        }
    }

    @CallbackProp
    fun setOnGenderChangedCallback(onGenderChangedListener: stringCallback) =
        editableEmployeeGenderRadioGroup.setOnCheckedChangeListener { radioGroup, buttonId ->
            val buttonText = radioGroup?.findViewById<RadioButton>(buttonId)?.text
            onGenderChangedListener?.invoke(buttonText.toString())
        }

    @CallbackProp
    fun setOnFirstNameChangedCallback(textChangedCallback: stringCallback) =
        editableEmployeeFirstNameTextEdit.doAfterTextChanged { editable ->
            editable?.onNotBlankCallback { textChangedCallback?.invoke(editable.toString()) }
        }

    @CallbackProp
    fun setOnLastNameChangedCallback(textChangedCallback: stringCallback) =
        editableEmployeeLastNameTextEdit.doAfterTextChanged { editable ->
            editable?.onNotBlankCallback { textChangedCallback?.invoke(editable.toString()) }
        }

    @CallbackProp
    fun setOnAgeChangedCallback(textChangedCallback: stringCallback) =
        editableEmployeeAgeTextEdit.doAfterTextChanged { editable ->
            editable?.onNotBlankCallback { textChangedCallback?.invoke(editable.toString()) }
        }

    @OnViewRecycled
    fun onViewRecycled() {
        editableEmployeeFirstNameTextEdit.setText("")
        editableEmployeeLastNameTextEdit.setText("")
        editableEmployeeAgeTextEdit.setText("")
        editableEmployeeGenderRadioGroup.clearCheck()
    }

    companion object {
        private val AGE_RANGE = 0..100
    }
}