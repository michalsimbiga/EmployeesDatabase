package com.employeesdatabase.models

data class EmployeeItem(
    val firstName: String = "",
    val lastName: String = "",
    val age: Int = -1,
    val gender: String = "",
    val addressess: List<AddressItem> = listOf()
)

fun Employee.toItem() =
    EmployeeItem(
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toItem() }
    )

fun EmployeeItem.toDomain() =
    Employee(
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toDomain() }
    )