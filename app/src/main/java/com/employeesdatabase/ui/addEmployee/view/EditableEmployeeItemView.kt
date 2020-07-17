package com.employeesdatabase.ui.addEmployee.view

import android.content.Context
import android.os.Parcelable
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
import com.employeesdatabase.models.EmployeeItem
import kotlinx.android.synthetic.main.fragment_add_editable_employee_item.view.*
import timber.log.Timber

typealias stringCallback = ((String) -> Unit)?

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EditableEmployeeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.fragment_add_editable_employee_item, this)
    }

    @ModelProp
    fun setEmployeeModel(employee: EmployeeItem?) {
        Timber.i("TESTING setEmployeeModel $employee")
        editableEmployeeFirstNameTextEdit.setText(employee?.firstName)
        editableEmployeeLastNameTextEdit.setText(employee?.lastName)
        employee?.age?.let { age ->
            if (age in 0..100) editableEmployeeAgeTextEdit.setText(age.toString())
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
            Timber.i("TESTING setOnGenderChangedCallback $buttonId")
            val buttonText = radioGroup?.findViewById<RadioButton>(buttonId)?.text
            onGenderChangedListener?.invoke(buttonText.toString())
        }

    @CallbackProp
    fun setOnFirstNameChangedCallback(onFirstNameTextChangedListener: stringCallback) =
        editableEmployeeFirstNameTextEdit.doAfterTextChanged { editable ->
            Timber.i("TESTING setOnFirstNameChangedCallback $editable")
            if (editable?.isNotBlank() == true) onFirstNameTextChangedListener?.invoke(editable.toString())
        }

    @CallbackProp
    fun setOnLastNameChangedCallback(onLastNameChangedListener: stringCallback) =
        editableEmployeeLastNameTextEdit.doAfterTextChanged { editable ->
            Timber.i("TESTING setOnLastNameChangedCallback $editable")
            if (editable?.isNotBlank() == true) onLastNameChangedListener?.invoke(editable.toString())
        }

    @CallbackProp
    fun setOnAgeChangedCallback(onAgeChangedListener: stringCallback) =
        editableEmployeeAgeTextEdit.doAfterTextChanged { editable ->
            Timber.i("TESTING setOnAgeChangedCallback $editable")
            if (editable?.isNotBlank() == true) onAgeChangedListener?.invoke(editable.toString())
        }

    @OnViewRecycled
    fun onViewRecycled() {
        editableEmployeeFirstNameTextEdit.clearComposingText()
        editableEmployeeLastNameTextEdit.clearComposingText()
        editableEmployeeAgeTextEdit.clearComposingText()
        editableEmployeeGenderRadioGroup.clearCheck()
    }
}