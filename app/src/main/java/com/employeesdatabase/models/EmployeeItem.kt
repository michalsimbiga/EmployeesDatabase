package com.employeesdatabase.models

import android.os.Parcelable
import com.employeesdatabase.common.UNINITIALIZED
import com.employeesdatabase.common.empty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployeeItem(
    val id: Long = UNINITIALIZED,
    val firstName: String = String.empty,
    val lastName: String = String.empty,
    val age: Int = UNINITIALIZED.toInt(),
    val gender: String = String.empty,
    val addressess: List<AddressItem> = listOf()
) : Parcelable {
    fun isEmpty() =
        firstName.trim() == String.empty && lastName.trim() == String.empty && age.toLong() == UNINITIALIZED && gender.trim() == String.empty
}

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