package com.employeesdatabase.ui.addEmployee

import com.airbnb.epoxy.EpoxyController
import com.employeesdatabase.R
import com.employeesdatabase.ui.addEmployee.view.addAddressButtonItemView
import com.employeesdatabase.ui.addEmployee.view.genderInputItemView
import com.employeesdatabase.ui.addEmployee.view.headerItemView
import com.employeesdatabase.ui.addEmployee.view.textInputItemView
import timber.log.Timber

class AddEmployeeEpoxyController : EpoxyController() {

    override fun buildModels() {
        headerItemView {
            id("asdasd")
            headerText(R.string.employee_detail_header)
        }
        textInputItemView {
            id("xcv")
            hint(R.string.first_name_hint)
            onTextChangedCallback { newFirstName -> Timber.i("TESTING $newFirstName") }
            isNumericalInput(false)
        }
        textInputItemView {
            id("asd")
            hint(R.string.second_name_hint)
            onTextChangedCallback { newSecondName -> Timber.i("TESTING $newSecondName") }
            isNumericalInput(false)
        }
        textInputItemView {
            id("zxc")
            hint(R.string.age_name_hint)
            onTextChangedCallback { newAge -> Timber.i("TESTING $newAge") }
            isNumericalInput(true)
        }
        headerItemView {
            id("zxca")
            headerText(R.string.gender_header)
        }
        genderInputItemView {
            id("az")
            onRadioButtonChanged { buttonText -> Timber.i("TESTING ${buttonText}") }
        }
        addAddressButtonItemView {
            id("bdf")
        }
    }
}