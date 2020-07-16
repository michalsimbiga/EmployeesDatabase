package com.employeesdatabase.dao

import com.employeesdatabase.AddressDbQueries
import com.employeesdatabase.EmployeesDbQueries

class EmployeesDao(
    private val employeesDbQueries: EmployeesDbQueries,
    private val addressDbQueries: AddressDbQueries
)