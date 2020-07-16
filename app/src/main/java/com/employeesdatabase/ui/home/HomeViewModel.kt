package com.employeesdatabase.ui.home

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.employeesdatabase.common.KoinMvRxViewModelFactory
import com.employeesdatabase.common.MvRxViewModel
import com.employeesdatabase.models.Employee
import com.employeesdatabase.models.toItem
import com.employeesdatabase.useCase.GetAllEmployeesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    state: HomeViewState,
    private val getAllEmployeesUseCase: GetAllEmployeesUseCase
) : MvRxViewModel<HomeViewState>(state) {

    fun getAllEmployees() = viewModelScope.launch(Dispatchers.Default) {
        getAllEmployeesUseCase.invoke(
            onSuccess = { setState { copy(listOfEmployees = Success(it.map(Employee::toItem))) } },
            onFailure = { setState { copy(listOfEmployees = Fail(it)) } }
        )
    }

    companion object :
        KoinMvRxViewModelFactory<HomeViewModel, HomeViewState>(HomeViewModel::class)
}