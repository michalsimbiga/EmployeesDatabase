package com.employeesdatabase.ui.edit

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.Success
import com.employeesdatabase.common.KoinMvRxViewModelFactory
import com.employeesdatabase.common.MvRxViewModel
import com.employeesdatabase.models.AddressItem
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.models.toDomain
import com.employeesdatabase.models.toItem
import com.employeesdatabase.useCase.DeleteAddressByIdUseCase
import com.employeesdatabase.useCase.GetEmployeeByIdUseCase
import com.employeesdatabase.useCase.InsertOrReplaceEmployeeUseCase
import com.employeesdatabase.useCase.UpdateEmployeeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(
    state: EditViewState,
    private val insertOrReplaceEmployeeUseCase: InsertOrReplaceEmployeeUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase,
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase,
    private val deleteAddressByIdUseCase: DeleteAddressByIdUseCase
) : MvRxViewModel<EditViewState>(state) {

    fun setEditMode() = setState { copy(inEditMode = true) }

    fun editOrAddEmployee(employee: EmployeeItem) = withState { state ->
        if (employee.isEmpty().not()) {
            if (state.inEditMode) updateEmployee(employee) else addEmployee(employee)
        }
    }

    private fun addEmployee(employee: EmployeeItem) =
        viewModelScope.launch(Dispatchers.Default) {
            insertOrReplaceEmployeeUseCase.execute(
                params = InsertOrReplaceEmployeeUseCase.Params(employee = employee.toDomain()),
                stateReducer = { copy(userOperationResult = it, navigateForward = Success(Unit)) }
            )
        }

    private fun updateEmployee(employee: EmployeeItem) =
        viewModelScope.launch(Dispatchers.Default) {
            updateEmployeeUseCase.execute(
                params = UpdateEmployeeUseCase.Params(employee = employee.toDomain()),
                stateReducer = { copy(userOperationResult = it, navigateForward = Success(Unit)) }
            )
        }

    fun getEmployeeById(employeeId: Long) =
        viewModelScope.launch(Dispatchers.Default) {
            getEmployeeByIdUseCase.execute(
                params = GetEmployeeByIdUseCase.Params(employeeId),
                mapper = { employee -> employee.toItem() },
                stateReducer = { copy(editableEmployee = it) }
            )
        }

    fun deleteAddress(address: AddressItem) =
        viewModelScope.launch(Dispatchers.Default) {
            deleteAddressByIdUseCase.execute(
                params = DeleteAddressByIdUseCase.Params(address = address.toDomain()),
                stateReducer = { copy(userOperationResult = it) }
            )
        }

    companion object :
        KoinMvRxViewModelFactory<EditViewModel, EditViewState>(EditViewModel::class)
}
