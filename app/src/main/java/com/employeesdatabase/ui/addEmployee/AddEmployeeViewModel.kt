package com.employeesdatabase.ui.addEmployee

import com.employeesdatabase.common.KoinMvRxViewModelFactory
import com.employeesdatabase.common.MvRxViewModel

class AddEmployeeViewModel(
    state: AddEmployeeViewState
) : MvRxViewModel<AddEmployeeViewState>(state) {

    companion object :
        KoinMvRxViewModelFactory<AddEmployeeViewModel, AddEmployeeViewState>(AddEmployeeViewModel::class)
}