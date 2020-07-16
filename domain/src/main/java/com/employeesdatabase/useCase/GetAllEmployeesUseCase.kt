package com.employeesdatabase.useCase

import com.employeesdatabase.common.NoParameterUseCase
import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository

class GetAllEmployeesUseCase(private val employeesRepository: EmployeesRepository) :
    NoParameterUseCase<List<Employee>>() {

    override suspend fun run(): Result<List<Employee>> =
        employeesRepository.getAllEmployees()
}
