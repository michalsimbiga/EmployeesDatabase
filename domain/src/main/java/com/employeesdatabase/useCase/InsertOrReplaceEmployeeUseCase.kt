package com.employeesdatabase.useCase

import com.employeesdatabase.common.Result
import com.employeesdatabase.common.UseCase
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository

class InsertOrReplaceEmployeeUseCase(private val employeesRepository: EmployeesRepository) :
    UseCase<Unit, InsertOrReplaceEmployeeUseCase.Params>() {

    override suspend fun run(params: Params): Result<Unit> =
        employeesRepository.insertEmployee(params.employee)

    data class Params(val employee: Employee)
}