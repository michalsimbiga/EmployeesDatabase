package com.employeesdatabase.repository

import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Address
import com.employeesdatabase.models.Employee

interface EmployeesRepository {

    suspend fun getAllEmployees(): Result<List<Employee>>
    suspend fun getEmployeeById(employeeId: Long): Result<Employee>
    suspend fun insertEmployee(employee: Employee): Result<Unit>
    suspend fun updateEmployee(employee: Employee): Result<Unit>
    suspend fun deleteEmployee(employee: Employee): Result<Unit>
    suspend fun deleteAddress(address: Address): Result<Unit>
}
