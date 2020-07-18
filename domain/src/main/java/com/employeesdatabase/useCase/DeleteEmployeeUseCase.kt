package com.employeesdatabase.useCase

import com.employeesdatabase.common.Result
import com.employeesdatabase.common.useCase.UseCase
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository

class DeleteEmployeeUseCase(private val employeesRepository: EmployeesRepository) :
    UseCase<Unit, DeleteEmployeeUseCase.Params>() {

    override suspend fun run(params: Params): Result<Unit> =
        employeesRepository.deleteEmployee(params.employee)

    data class Params(val employee: Employee)
}