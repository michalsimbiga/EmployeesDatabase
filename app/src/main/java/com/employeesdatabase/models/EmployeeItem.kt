package com.employeesdatabase.models

data class EmployeeItem(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: Int,
    val addressess: List<AddressItem>
)