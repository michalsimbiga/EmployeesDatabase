package com.employeesdatabase.models

import com.employeesdatabase.AddressDb

data class AddressEntity(
    val street: String,
    val city: String,
    val zip: String,
    val country: String
)

fun AddressDb.toEntity() =
    AddressEntity(
        street = street,
        city = city,
        zip = zip,
        country = country
    )

fun AddressEntity.toDomain() =
    Address(
        street = street,
        city = city,
        zip = zip,
        country = country
    )

fun Address.toEntity() =
    AddressEntity(
        street = street,
        city = city,
        zip = zip,
        country = country
    )