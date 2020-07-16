package com.employeesdatabase.ui.addEmployee

import com.employeesdatabase.common.KoinMvRxViewModelFactory
import com.employeesdatabase.common.MvRxViewModel
import com.employeesdatabase.models.EmployeeItem

class AddEmployeeViewModel(
    state: AddEmployeeViewState
) : MvRxViewModel<AddEmployeeViewState>(state) {

    fun addEmployee(employee: EmployeeItem){

    }

    companion object :
        KoinMvRxViewModelFactory<AddEmployeeViewModel, AddEmployeeViewState>(AddEmployeeViewModel::class)
}