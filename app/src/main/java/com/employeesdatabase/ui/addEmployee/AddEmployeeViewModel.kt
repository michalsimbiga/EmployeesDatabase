package com.employeesdatabase.ui.addEmployee

import androidx.lifecycle.viewModelScope
import com.employeesdatabase.common.KoinMvRxViewModelFactory
import com.employeesdatabase.common.MvRxViewModel
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.models.toDomain
import com.employeesdatabase.useCase.InsertOrReplaceEmployeeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class AddEmployeeViewModel(
    state: AddEmployeeViewState,
    private val insertOrReplaceEmployeeUseCase: InsertOrReplaceEmployeeUseCase
) : MvRxViewModel<AddEmployeeViewState>(state) {

    fun addEmployee(employee: EmployeeItem) = viewModelScope.launch(Dispatchers.Default) {
        insertOrReplaceEmployeeUseCase.invoke(
            params = InsertOrReplaceEmployeeUseCase.Params(employee.toDomain()),
            onSuccess = { Timber.i("TESTING insertOrReplaceEmployeeUseCase success")},
            onFailure = { Timber.i("TESTING insertOrReplaceEmployeeUseCase failure") }
        )
    }

    companion object :
        KoinMvRxViewModelFactory<AddEmployeeViewModel, AddEmployeeViewState>(AddEmployeeViewModel::class)
}