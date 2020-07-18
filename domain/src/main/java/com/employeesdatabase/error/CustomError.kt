package com.employeesdatabase.error

sealed class CustomError : CustomException() {

    data class Unknown(override val originalException: Throwable? = null) : CustomError()
}