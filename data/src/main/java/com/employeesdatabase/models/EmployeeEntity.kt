package com.employeesdatabase.models

import com.employeesdatabase.EmployeeDb

data class EmployeeEntity(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val addressess: List<AddressEntity>
)

fun EmployeeEntity.toDomain() =
    Employee(
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toDomain() }
    )

fun Employee.toEntity() =
    EmployeeEntity(
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toEntity() }
    )