package com.employeesdatabase.models

import com.employeesdatabase.EmployeeDb

data class EmployeeEntity(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val addressess: List<AddressEntity>
)

fun EmployeeEntity.toDomain() =
    Employee(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toDomain() }
    )

fun EmployeeDb.toEntity() =
    EmployeeEntity(
        id = id.toInt(),
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = listOf()
    )

fun Employee.toEntity() =
    EmployeeEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toEntity() }
    )