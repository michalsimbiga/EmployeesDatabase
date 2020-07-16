package com.employeesdatabase.ui.home

import com.airbnb.epoxy.TypedEpoxyController
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.ui.home.view.employeeAddressItemView
import com.employeesdatabase.ui.home.view.employeeItemView

class HomeEpoxyController : TypedEpoxyController<List<EmployeeItem>>() {

    override fun buildModels(employeeList: List<EmployeeItem>?) {
        employeeList?.forEachIndexed { index, employeeItem ->
            employeeItemView {
                id(index)
                employeeModel(employeeItem)
            }
            employeeItem.addressess.forEachIndexed { index, addressItem ->
                employeeAddressItemView {
                    id(index)
                    addressModel(addressItem)
                }
            }
        }
    }
}