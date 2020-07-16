package com.employeesdatabase.ui.home.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.employeesdatabase.R
import com.employeesdatabase.models.EmployeeItem
import kotlinx.android.synthetic.main.fragment_home_employee_item.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EmployeeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.fragment_home_employee_item, this)
    }

    @ModelProp
    fun setEmployeeModel(employee: EmployeeItem?) {
        val fullName = employee?.firstName + " " + employee?.lastName
        homeEmployeeName.text = fullName.capitalize()
        homeEmployeeAge.text = employee?.age.toString().capitalize()
        homeEmployeeGender.text = employee?.gender?.capitalize()
    }

    @OnViewRecycled
    fun onViewRecycled(){
        homeEmployeeName.text = ""
        homeEmployeeAge.text = ""
        homeEmployeeGender.text = ""
    }
}