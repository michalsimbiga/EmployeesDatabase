package com.employeesdatabase.ui.addEmployee

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.employeesdatabase.common.Result

data class AddEmployeeViewState(val addUserResult: Async<Unit> = Uninitialized) : MvRxState