package com.employeesdatabase.models

import android.os.Parcelable
import com.employeesdatabase.common.UNINITIALIZED
import com.employeesdatabase.common.empty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressItem(
    val id: Long = UNINITIALIZED,
    val street: String = String.empty,
    val city: String = String.empty,
    val zip: String = String.empty,
    val country: String = String.empty,
    val editable: Boolean = true
) : Parcelable {
    fun isEmpty() =
        street.trim() == String.empty &&
                city.trim() == String.empty &&
                zip.trim() == String.empty &&
                country.trim() == String.empty
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
