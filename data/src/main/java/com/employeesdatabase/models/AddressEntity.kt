package com.employeesdatabase.models

import com.employeesdatabase.AddressDb

data class AddressEntity(
    val id: Long? = null,
    val street: String,
    val city: String,
    val zip: String,
    val country: String
)

fun AddressDb.toEntity() =
    AddressEntity(
        id = id,
        street = street,
        city = city,
        zip = zip,
        country = country
    )

fun AddressEntity.toDomain() =
    Address(
        id = id ?: -1,
        street = street,
        city = city,
        zip = zip,
        country = country
    )

fun Address.toEntity() =
    AddressEntity(
        id = id,
        street = street,
        city = city,
        zip = zip,
        country = country
    )