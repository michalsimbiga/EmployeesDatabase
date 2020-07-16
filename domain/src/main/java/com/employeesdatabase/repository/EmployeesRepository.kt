package com.employeesdatabase.repository

import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Employee

interface EmployeesRepository {

    suspend fun getAllEmployees(): Result<List<Employee>>
    suspend fun getEmployeeById(): Result<Employee>
    suspend fun insertEmployee(employee: Employee): Result<Unit>
    suspend fun deleteEmployee(employee: Employee): Result<Unit>

}