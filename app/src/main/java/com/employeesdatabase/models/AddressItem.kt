package com.employeesdatabase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressItem(
    val id: Long = -1,
    val street: String = "",
    val city: String = "",
    val zip: String = "",
    val country: String = "",
    val editable: Boolean = true
) : Parcelable {
    fun isEmpty() =
        street.trim() == "" && city.trim() == "" && zip.trim() == "" && country.trim() == ""
}

fun AddressItem.toDomain() =
    Address(
        id = id,
        street = street,
        city = city,
        zip = zip,
        country = country
    )

fun Address.toItem() =
    AddressItem(
        id = id,
        street = street,
        city = city,
        zip = zip,
        country = country,
        editable = false
    )
