package com.employeesdatabase.useCase

import com.employeesdatabase.common.Result
import com.employeesdatabase.common.useCase.UseCase
import com.employeesdatabase.models.Address
import com.employeesdatabase.repository.EmployeesRepository

class DeleteAddressByIdUseCase(private val employeesRepository: EmployeesRepository) :
    UseCase<Unit, DeleteAddressByIdUseCase.Params>() {

    override suspend fun run(params: Params): Result<Unit> =
        employeesRepository.deleteAddress(params.address)

    data class Params(val address: Address)
}