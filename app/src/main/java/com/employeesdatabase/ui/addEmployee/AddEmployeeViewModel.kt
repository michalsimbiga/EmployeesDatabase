package com.employeesdatabase.ui.addEmployee

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.employeesdatabase.common.KoinMvRxViewModelFactory
import com.employeesdatabase.common.MvRxViewModel
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.models.toDomain
import com.employeesdatabase.useCase.InsertOrReplaceEmployeeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEmployeeViewModel(
    state: AddEmployeeViewState,
    private val insertOrReplaceEmployeeUseCase: InsertOrReplaceEmployeeUseCase
) : MvRxViewModel<AddEmployeeViewState>(state) {

    fun addEmployee(employee: EmployeeItem) = viewModelScope.launch(Dispatchers.Default) {
        insertOrReplaceEmployeeUseCase.invoke(
            params = InsertOrReplaceEmployeeUseCase.Params(employee.toDomain()),
            onSuccess = { setState { copy(addUserResult = Success(it)) }},
            onFailure = { setState { copy(addUserResult = Fail(it)) } }
        )
    }

    companion object :
        KoinMvRxViewModelFactory<AddEmployeeViewModel, AddEmployeeViewState>(AddEmployeeViewModel::class)
}