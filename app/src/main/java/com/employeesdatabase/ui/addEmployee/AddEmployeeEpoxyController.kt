package com.employeesdatabase.ui.addEmployee

import android.os.Bundle
import com.airbnb.epoxy.EpoxyController
import com.employeesdatabase.models.AddressItem
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.ui.addEmployee.view.*
import com.employeesdatabase.ui.common.emptyItemView
import timber.log.Timber

class AddEmployeeEpoxyController : EpoxyController() {

    private val listOfAddresses = mutableListOf<AddressItem>()
    private var employee: EmployeeItem = EmployeeItem()

    fun setNewEmployee(newEmployee: EmployeeItem) {
        employee = newEmployee
        listOfAddresses.addAll(newEmployee.addressess)
    }

    override fun buildModels() {
        Timber.i("TESTING buildModels $employee")
        editableEmployeeItemView {
            id(EMPLOYEE_ITEM_VIEW_ID)
            employeeModel(employee)
            onFirstNameChangedCallback { firstName -> setEmployee { copy(firstName = firstName) } }
            onLastNameChangedCallback { lastName -> setEmployee { copy(lastName = lastName) } }
            onAgeChangedCallback { age -> setEmployee { copy(age = age.toInt()) } }
            onGenderChangedCallback { gender -> setEmployee { copy(gender = gender) } }
        }
        listOfAddresses.forEachIndexed { index, addressItem ->
            if (addressItem.editable.not()) {
                addressItemView {
                    id(index)
                    addressModel(addressItem)
                    onEditButtonCallback { _ ->
                        withRequestModelBuild {
                            with(listOfAddresses) {
                                removeAt(index)
                                add(index, addressItem.copy(editable = true))
                            }
                        }
                    }
                    onDiscardButtonCallback { _ ->
                        withRequestModelBuild { listOfAddresses.removeAt(index) }
                    }
                }
            } else {
                editableAddressItemView {
                    id(index)
                    addressModel(addressItem)
                    onDiscardButtonCallback { _ ->
                        withRequestModelBuild { listOfAddresses.removeAt(index) }
                    }
                    onAddButtonCallback { addressModel ->
                        withRequestModelBuild {
                            with(listOfAddresses) {
                                removeAt(index)
                                add(addressModel)
                            }
                        }
                    }
                }
            }
        }
        addAddressButtonItemView {
            id(ADD_ADDRESS_ITEM_VIEW_ID)
            onAddAddressCallback { _ -> withRequestModelBuild { listOfAddresses.add(AddressItem()) } }
        }
        emptyItemView {
            id(EMPTY_ITEM_VIEW_ID)
        }
    }

    fun getEmployee() = employee.copy(addressess = listOfAddresses.filterNot(AddressItem::isEmpty))

    private fun setEmployee(block: EmployeeItem.() -> EmployeeItem) {
        employee = block(employee)
    }

    private fun withRequestModelBuild(callback: () -> Unit) {
        callback()
        requestModelBuild()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(EMPLOYEE_PARCEL_KEY, employee)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(inState: Bundle?) {
        inState?.getParcelable<EmployeeItem>(EMPLOYEE_PARCEL_KEY)?.let { employee = it }

        super.onRestoreInstanceState(inState)
    }

    companion object {
        private const val EMPLOYEE_PARCEL_KEY = "Employee"
        private const val EMPLOYEE_ITEM_VIEW_ID = "HomeEpoxyControllerEmployeeItemId"
        private const val EMPTY_ITEM_VIEW_ID = "HomeEpoxyControllerEmptyItemId"
        private const val ADD_ADDRESS_ITEM_VIEW_ID = "HomeEpoxyControllerAddAddressItemId"
    }
}