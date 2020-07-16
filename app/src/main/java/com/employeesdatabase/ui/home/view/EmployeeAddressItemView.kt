package com.employeesdatabase.ui.home.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.employeesdatabase.R
import com.employeesdatabase.models.AddressItem
import kotlinx.android.synthetic.main.fragment_home_employee_address_item.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EmployeeAddressItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.fragment_home_employee_address_item, this)
    }

    @ModelProp
    fun setAddressModel(address: AddressItem?) {
        homeEmployeeAddressStreet.text = address?.street
        homeEmployeeAddressCity.text = address?.city
        homeEmployeeAddressZip.text = address?.zip
        homeEmployeeAddressCountry.text = address?.country
    }

    @OnViewRecycled
    fun onViewRecycled() {
        homeEmployeeAddressStreet.text = ""
        homeEmployeeAddressCity.text = ""
        homeEmployeeAddressZip.text = ""
        homeEmployeeAddressCountry.text = ""
    }
}