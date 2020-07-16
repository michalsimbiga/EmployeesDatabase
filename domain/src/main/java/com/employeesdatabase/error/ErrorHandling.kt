package com.employeesdatabase.error

import java.net.SocketTimeoutException

fun Throwable.toCustomError(): CustomError = throw this
