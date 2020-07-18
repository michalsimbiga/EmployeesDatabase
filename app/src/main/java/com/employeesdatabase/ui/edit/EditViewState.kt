package com.employeesdatabase.ui.edit

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.employeesdatabase.models.EmployeeItem

data class EditViewState(
    val editableEmployee: Async<EmployeeItem> = Uninitialized,
    val userOperationResult: Async<Unit> = Uninitialized,
    val inEditMode: Boolean = false,
    val navigateForward: Async<Unit> = Uninitialized
) : MvRxState