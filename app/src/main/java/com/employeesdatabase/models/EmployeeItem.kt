package com.employeesdatabase.models

data class EmployeeItem(
    val firstName: String = "",
    val lastName: String = "",
    val age: Int = -1,
    val gender: String = "",
    val addressess: List<AddressItem> = listOf()
)