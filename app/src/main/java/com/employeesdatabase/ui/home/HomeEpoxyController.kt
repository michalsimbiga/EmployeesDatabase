package com.employeesdatabase.ui.home

import com.airbnb.epoxy.TypedEpoxyController
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.ui.common.emptyItemView
import com.employeesdatabase.ui.home.view.employeeAddressItemView
import com.employeesdatabase.ui.home.view.employeeItemView

typealias employeeItemCallback = ((EmployeeItem) -> Unit)?

class HomeEpoxyController(
    private var onDeleteEmployeeCallback: employeeItemCallback = null,
    private var onEditEmployeeCallback: employeeItemCallback = null
) : TypedEpoxyController<List<EmployeeItem>>() {

    override fun buildModels(employeeList: List<EmployeeItem>?) {
        employeeList?.forEach { employeeItem ->
            employeeItemView {
                id(employeeItem.hashCode())
                employeeModel(employeeItem)
                onDeleteButtonCallback { _ -> onDeleteEmployeeCallback?.invoke(employeeItem) }
                onEditButtonCallback { _ -> onEditEmployeeCallback?.invoke(employeeItem) }
            }
            employeeItem.addressess.forEach { addressItem ->
                employeeAddressItemView {
                    id(addressItem.hashCode())
                    addressModel(addressItem)
                }
            }
        }
        emptyItemView {
            id(EMPTY_ITEM_VIEW_ID)
        }
    }

    fun clearCallbacks() {
        onDeleteEmployeeCallback = null
        onEditEmployeeCallback = null
    }

    companion object {
        private const val EMPTY_ITEM_VIEW_ID = "HomeEpoxyControllerEmptyItemId"
    }
}
