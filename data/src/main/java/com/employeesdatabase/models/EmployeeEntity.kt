package com.employeesdatabase.models

data class EmployeeEntity(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val addressess: List<AddressEntity>
)

//fun EmployeeDb.toEntity() =
//    EmployeeEntity(
//        firstName = firstName,
//        lastName = lastName,
//        age = age.toInt(),
//        gender = gender,
//        addressess = addressess
//    )

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