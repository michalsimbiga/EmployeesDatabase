package com.employeesdatabase.models

data class Employee(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val addressess: List<Address>
)