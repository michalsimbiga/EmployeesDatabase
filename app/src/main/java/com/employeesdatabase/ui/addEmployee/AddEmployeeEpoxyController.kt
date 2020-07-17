package com.employeesdatabase.ui.addEmployee

import android.os.Bundle
import com.airbnb.epoxy.EpoxyController
import com.employeesdatabase.R
import com.employeesdatabase.models.AddressItem
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.ui.addEmployee.view.*
import com.employeesdatabase.ui.common.emptyItemView
import timber.log.Timber

class AddEmployeeEpoxyController : EpoxyController() {

    init {
        Timber.i("TESTING init AddEmployeeEpoxy")
    }

    private val listOfAddresses = mutableListOf<AddressItem>()
    private var employee: EmployeeItem = EmployeeItem()
        set(value) {
            field = value
            Timber.i("TESTING newEmployee $value")
        }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.i("TESTING onSAveInstanceState $employee")
        outState.putParcelable("Employee", employee)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(inState: Bundle?) {
        inState?.getParcelable<EmployeeItem>("Employee")?.let { savedEmployee ->
            Timber.i("TESTING savedEmployee $savedEmployee")
            employee = savedEmployee
        }
        super.onRestoreInstanceState(inState)
    }

    override fun buildModels() {
        headerItemView {
            id(HEADER_ITEM_VIEW_ID)
            headerText(R.string.employee_detail_header)
        }
        editableEmployeeItemView {
            id(EMPLOYEE_ITEM_VIEW_ID)
            employeeModel(employee)
            onFirstNameChangedCallback { firstName ->
                employee = employee.copy(firstName = firstName)
            }
            onLastNameChangedCallback { lastName ->
                employee = employee.copy(lastName = lastName)
            }
            onAgeChangedCallback { age -> employee = employee.copy(age = age.toInt()) }
            onGenderChangedCallback { gender -> employee = employee.copy(gender = gender) }
        }
        listOfAddresses.forEachIndexed { index, addressItem ->
            if (addressItem.editable.not()) {
                addressItemView {
                    id(index)
                    addressModel(addressItem)
                    onEditButtonCallback { _ ->
                        listOfAddresses.removeAt(index)
                        listOfAddresses.add(index, addressItem.copy(editable = true))
                        requestModelBuild()
                    }
                    onDiscardButtonCallback { _ ->
                        listOfAddresses.removeAt(index)
                        requestModelBuild()
                    }
                }
            } else {
                editableAddressItemView {
                    id(index)
                    addressModel(addressItem)
                    onDiscardButtonCallback { _ ->
                        listOfAddresses.removeAt(index)
                        requestModelBuild()
                    }
                    onAddButtonCallback { addressModel ->
                        Timber.i("TESTING onAddButtonClicked $addressModel")
                        listOfAddresses.removeAt(index)
                        listOfAddresses.add(addressModel)
                        requestModelBuild()
                    }
                }
            }

        }
        addAddressButtonItemView {
            id(ADD_ADDRESS_ITEM_VIEW_ID)
            onAddAddressCallback { _ ->
                listOfAddresses.add(AddressItem())
                requestModelBuild()
            }
        }
        emptyItemView {
            id(EMPTY_ITEM_VIEW_ID)
        }
    }

    fun getEmployee(): EmployeeItem? {
        Timber.i("TESTING getEmployee $employee ${listOfAddresses}")
        return employee.copy(addressess = listOfAddresses.filterNot(AddressItem::isEmpty))
    }

    companion object {
        private const val EMPLOYEE_ITEM_VIEW_ID = "HomeEpoxyControllerEmployeeItemId"
        private const val EMPTY_ITEM_VIEW_ID = "HomeEpoxyControllerEmptyItemId"
        private const val HEADER_ITEM_VIEW_ID = "HomeEpoxyControllerHeaderItemId"
        private const val ADD_ADDRESS_ITEM_VIEW_ID = "HomeEpoxyControllerAddAddressItemId"
    }
}