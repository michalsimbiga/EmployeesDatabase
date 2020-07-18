package com.employeesdatabase.ui.edit

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized

data class EditViewState(
    val addUserResult: Async<Unit> = Uninitialized,
    val editingEmployee: Boolean = false
) : MvRxState