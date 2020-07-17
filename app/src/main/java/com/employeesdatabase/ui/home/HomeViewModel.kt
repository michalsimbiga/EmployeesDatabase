package com.employeesdatabase.ui.home

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.employeesdatabase.common.KoinMvRxViewModelFactory
import com.employeesdatabase.common.MvRxViewModel
import com.employeesdatabase.models.Employee
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.models.toDomain
import com.employeesdatabase.models.toItem
import com.employeesdatabase.useCase.DeleteEmployeeUseCase
import com.employeesdatabase.useCase.GetAllEmployeesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    state: HomeViewState,
    private val getAllEmployeesUseCase: GetAllEmployeesUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase
) : MvRxViewModel<HomeViewState>(state) {

    fun getAllEmployees() = viewModelScope.launch(Dispatchers.Default) {
        getAllEmployeesUseCase.invoke(
            onSuccess = { setState { copy(listOfEmployees = Success(it.map(Employee::toItem))) } },
            onFailure = { setState { copy(listOfEmployees = Fail(it)) } }
        )
    }

    fun deleteEmployee(employee: EmployeeItem) = viewModelScope.launch(Dispatchers.Default) {
        deleteEmployeeUseCase.invoke(
            params = DeleteEmployeeUseCase.Params(employee.toDomain()),
            onSuccess = { getAllEmployees() },
            onFailure = { setState { copy(listOfEmployees = Fail(it)) } }
        )
    }


    companion object :
        KoinMvRxViewModelFactory<HomeViewModel, HomeViewState>(HomeViewModel::class)
}