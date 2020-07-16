package com.employeesdatabase.error

abstract class CustomException : Exception() {
    abstract val originalException: Throwable?
}
