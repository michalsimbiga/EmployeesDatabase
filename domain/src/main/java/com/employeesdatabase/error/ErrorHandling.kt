package com.employeesdatabase.error

fun Throwable.toCustomError(): CustomError = throw this
