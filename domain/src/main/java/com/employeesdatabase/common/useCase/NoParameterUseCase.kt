package com.employeesdatabase.common.useCase

import com.employeesdatabase.common.Result
import com.employeesdatabase.error.CustomError

abstract class NoParameterUseCase<out Type> where Type : Any {

    abstract suspend fun run(): Result<Type>

    suspend operator fun invoke(
        onSuccess: (Type) -> Unit = {},
        onFailure: (CustomError) -> Unit = {}
    ) = when (val result = run()) {
        is Result.Success -> onSuccess(result.data)
        is Result.Failure -> onFailure(result.error)
    }
}