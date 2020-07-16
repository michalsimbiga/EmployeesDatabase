package com.employeesdatabase.models

data class AddressItem(
    var street: String = "",
    var city: String = "",
    var zip: String = "",
    var country: String = "",
    var editable: Boolean = true
) {
    fun isEmpty() =
        street.trim() == "" && city.trim() == "" && zip.trim() == "" && country.trim() == ""
}

fun AddressItem.toDomain() =
    Address(
        street = street,
        city = city,
        zip = zip,
        country = country
    )

fun Address.toItem() =
    AddressItem(
        street = street,
        city = city,
        zip = zip,
        country = country,
        editable = false
    )