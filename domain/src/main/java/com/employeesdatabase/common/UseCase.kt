package com.employeesdatabase.common

import com.employeesdatabase.error.CustomError

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Type>

    suspend operator fun invoke(
        params: Params,
        onSuccess: (Type) -> Unit = {},
        onFailure: (CustomError) -> Unit = {}
    ) = when (val result = run(params)) {
        is Result.Success -> onSuccess(result.data)
        is Result.Failure -> onFailure(result.error)
    }
}
