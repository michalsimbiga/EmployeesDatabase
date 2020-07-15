package com.employeesdatabase

interface Database {
    fun employeeQueries(): EmployeesDbQueries
}