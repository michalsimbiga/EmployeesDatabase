package com.employeesdatabase.ui.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.employeesdatabase.models.EmployeeItem

data class HomeViewState(val listOfEmployees: Async<List<EmployeeItem>> = Uninitialized): MvRxState