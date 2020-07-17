package com.employeesdatabase.ui.addEmployee

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.employeesdatabase.common.KoinMvRxViewModelFactory
import com.employeesdatabase.common.MvRxViewModel
import com.employeesdatabase.models.AddressItem
import com.employeesdatabase.models.EmployeeItem
import com.employeesdatabase.models.toDomain
import com.employeesdatabase.useCase.DeleteAddressByIdUseCase
import com.employeesdatabase.useCase.InsertOrReplaceEmployeeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class AddEmployeeViewModel(
    state: AddEmployeeViewState,
    private val insertOrReplaceEmployeeUseCase: InsertOrReplaceEmployeeUseCase,
    private val deleteAddressByIdUseCase: DeleteAddressByIdUseCase
) : MvRxViewModel<AddEmployeeViewState>(state) {

    fun setEditMode() = setState { copy(editingEmployee = true) }

    fun addEmployee(employee: EmployeeItem) = viewModelScope.launch(Dispatchers.Default) {
        insertOrReplaceEmployeeUseCase.invoke(
            params = InsertOrReplaceEmployeeUseCase.Params(employee =employee.toDomain()),
            onSuccess = { setState { copy(addUserResult = Success(it)) } },
            onFailure = { setState { copy(addUserResult = Fail(it)) } }
        )
    }

    fun deleteAddress(address: AddressItem) = viewModelScope.launch(Dispatchers.Default) {
        deleteAddressByIdUseCase.invoke(
            params = DeleteAddressByIdUseCase.Params(address = address.toDomain()),
            onSuccess = { Timber.i("TESTING address Deleted")},
            onFailure = { Timber.i("TESTING cannot delete address")}
        )
    }

    companion object :
        KoinMvRxViewModelFactory<AddEmployeeViewModel, AddEmployeeViewState>(AddEmployeeViewModel::class)
}