package com.employeesdatabase.error


sealed class CustomError : CustomException() {

    data class RecordNotFound(override val originalException: Throwable? = null) : CustomError()

}