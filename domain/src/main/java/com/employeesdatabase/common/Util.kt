package com.employeesdatabase.common

val doNothing = Unit

const val UNINITIALIZED = -1L

val String.Companion.empty
    get() = ""

val String.Companion.blank
    get() = " "