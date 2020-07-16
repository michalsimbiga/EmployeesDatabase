package com.employeesdatabase.error


sealed class CustomError : CustomException() {

    data class DatabaseFailure(override val originalException: Throwable? = null) : CustomError()

}