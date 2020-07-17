package com.employeesdatabase.repository

import com.employeesdatabase.common.Result
import com.employeesdatabase.common.safeCall
import com.employeesdatabase.dataSource.LocalDataSource
import com.employeesdatabase.models.*

class EmployeesRepositoryImpl(private val localDataSource: LocalDataSource) : EmployeesRepository {

    override suspend fun getAllEmployees(): Result<List<Employee>> =
        safeCall {
            localDataSource.getAllEmployees()
                .map(EmployeeEntity::toDomain)
        }

    override suspend fun getEmployeeById(): Result<Employee> {
        TODO("Not yet implemented")
    }

    override suspend fun insertEmployee(employee: Employee): Result<Unit> =
        safeCall { localDataSource.insertEmployee(employee = employee.toEntity()) }

    override suspend fun editEmployee(employee: Employee): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmployee(employee: Employee): Result<Unit> =
        safeCall { localDataSource.deleteEmployee(employee = employee.toEntity()) }

    override suspend fun deleteAddress(address: Address): Result<Unit> =
        safeCall { localDataSource.deleteAddress(address = address.toEntity()) }
}