package com.employeesdatabase.models

data class Address(
    val id: Int,
    val street: String,
    val city: String,
    val zip: String,
    val country: String
)