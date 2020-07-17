package com.employeesdatabase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployeeItem(
    val id: Long = -1,
    val firstName: String = "",
    val lastName: String = "",
    val age: Int = -1,
    val gender: String = "",
    val addressess: List<AddressItem> = listOf()
): Parcelable

fun Employee.toItem() =
    EmployeeItem(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toItem() }
    )

fun EmployeeItem.toDomain() =
    Employee(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toDomain() }
    )