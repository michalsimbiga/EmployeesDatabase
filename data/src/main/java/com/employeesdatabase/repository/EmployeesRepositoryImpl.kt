package com.employeesdatabase.repository

import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Employee

class EmployeesRepositoryImpl : EmployeesRepository {

    override suspend fun getAllEmployees(): Result<List<Employee>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEmployeeById(): Result<Employee> {
        TODO("Not yet implemented")
    }

    override suspend fun insertEmployee(employee: Employee): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmployee(employee: Employee): Result<Unit> {
        TODO("Not yet implemented")
    }
}