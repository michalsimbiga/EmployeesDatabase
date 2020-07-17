package com.employeesdatabase.models

import com.employeesdatabase.EmployeeDb

data class EmployeeEntity(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val addressess: List<AddressEntity>
)

fun EmployeeEntity.toDomain() =
    Employee(
        id = id ?: -1,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toDomain() }
    )

fun EmployeeDb.toEntity() =
    EmployeeEntity(
        id = id,
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