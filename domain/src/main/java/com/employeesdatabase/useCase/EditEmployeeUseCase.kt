package com.employeesdatabase.useCase

import com.employeesdatabase.common.Result
import com.employeesdatabase.common.UseCase
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository

class EditEmployeeUseCase(private val employeesRepository: EmployeesRepository) :
    UseCase<Unit, EditEmployeeUseCase.Params>() {

    override suspend fun run(params: Params): Result<Unit> =
        employeesRepository.editEmployee(params.employee)

    data class Params(val employee: Employee)
}