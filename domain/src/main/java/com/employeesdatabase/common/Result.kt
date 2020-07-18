package com.employeesdatabase.common

import com.employeesdatabase.error.CustomError
import com.employeesdatabase.error.toCustomError

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: CustomError) : Result<Nothing>()

    operator fun invoke(): T? = (this as? Success)?.data
}

inline fun <T> safeCall(call: () -> T): Result<T> =
    try {
        Result.Success(call.invoke())
    } catch (exception: Exception) {
        Result.Failure(exception.toCustomError())
    }