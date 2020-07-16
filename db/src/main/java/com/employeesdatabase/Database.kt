package com.employeesdatabase

interface Database {
    fun employeeQueries(): EmployeesDbQueries
    fun addressQueries(): AddressDbQueries
}