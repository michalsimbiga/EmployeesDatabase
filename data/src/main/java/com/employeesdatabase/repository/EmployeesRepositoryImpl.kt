package com.employeesdatabase.repository

import com.employeesdatabase.common.Result
import com.employeesdatabase.common.safeCall
import com.employeesdatabase.dataSource.LocalDataSource
import com.employeesdatabase.models.Employee
import com.employeesdatabase.models.toEntity

class EmployeesRepositoryImpl(private val localDataSource: LocalDataSource) : EmployeesRepository {

    override suspend fun getAllEmployees(): Result<List<Employee>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEmployeeById(): Result<Employee> {
        TODO("Not yet implemented")
    }

    override suspend fun insertEmployee(employee: Employee): Result<Unit> =
        safeCall { localDataSource.insertEmployee(employee.toEntity()) }

    override suspend fun deleteEmployee(employee: Employee): Result<Unit> {
        TODO("Not yet implemented")
    }
}