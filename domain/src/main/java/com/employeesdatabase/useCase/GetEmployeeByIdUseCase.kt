package com.employeesdatabase.useCase

import com.employeesdatabase.common.Result
import com.employeesdatabase.common.UseCase
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository

class GetEmployeeByIdUseCase(private val employeesRepository: EmployeesRepository) :
    UseCase<Employee, GetEmployeeByIdUseCase.Params>() {

    override suspend fun run(params: Params): Result<Employee> =
        employeesRepository.getEmployeeById(params.employeeId)

    data class Params(val employeeId: Long)
}