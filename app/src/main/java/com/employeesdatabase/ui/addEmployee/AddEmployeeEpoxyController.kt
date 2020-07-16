package com.employeesdatabase.ui.addEmployee

import com.airbnb.epoxy.EpoxyController
import com.employeesdatabase.R
import com.employeesdatabase.models.AddressItem
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.ui.addEmployee.view.*
import com.employeesdatabase.ui.common.emptyItemView
import timber.log.Timber

class AddEmployeeEpoxyController : EpoxyController() {

    private val listOfAddressess = mutableListOf<AddressItem>()
    private var employee = EmployeeItem()

    override fun buildModels() {
        headerItemView {
            id("asdasd")
            headerText(R.string.employee_detail_header)
        }
        textInputItemView {
            id("xcv")
            hint(R.string.first_name_hint)
            onTextChangedCallback { newFirstName ->
                employee = employee.copy(firstName = newFirstName)
            }
            isNumericalInput(false)
        }
        textInputItemView {
            id("asd")
            hint(R.string.second_name_hint)
            onTextChangedCallback { newSecondName ->
                employee = employee.copy(lastName = newSecondName)
            }
            isNumericalInput(false)
        }
        textInputItemView {
            id("zxc")
            hint(R.string.age_name_hint)
            onTextChangedCallback { newAge ->
                employee = employee.copy(age = newAge.toInt())
            }
            isNumericalInput(true)
        }
        headerItemView {
            id("zxca")
            headerText(R.string.gender_header)
        }
        genderInputItemView {
            id("az")
            onRadioButtonChanged { buttonText -> employee = employee.copy(gender = buttonText) }
        }
        listOfAddressess.forEachIndexed { index, addressItem ->
            if (addressItem.editable.not()) {
                addressItemView {
                    id(index)
                    addressModel(addressItem)
                    onEditButtonCallback { _ ->
                        listOfAddressess.removeAt(index)
                        listOfAddressess.add(index, addressItem.copy(editable = true))
                        requestModelBuild()
                    }
                    onDiscardButtonCallback { _ ->
                        listOfAddressess.removeAt(index)
                        requestModelBuild()
                    }
                }
            } else {
                editableAddressItemView {
                    id(index)
                    addressModel(addressItem)
                    onDiscardButtonCallback { _ ->
                        listOfAddressess.removeAt(index)
                        requestModelBuild()
                    }
                    onAddButtonCallback { addressModel ->
                        Timber.i("TESTING onAddButtonClicked $addressModel")
                        listOfAddressess.removeAt(index)
                        listOfAddressess.add(addressModel)
                        requestModelBuild()
                    }
                }
            }

        }
        addAddressButtonItemView {
            id("bdf")
            onAddAddressCallback { _ ->
                Timber.i("TESTING buttonClicked")
                listOfAddressess.add(AddressItem())
                requestModelBuild()
            }
        }
        emptyItemView {
            id("asdazxc")
        }
    }

    fun getEmployee() = employee.copy(addressess = listOfAddressess.filterNot(AddressItem::isEmpty))
}