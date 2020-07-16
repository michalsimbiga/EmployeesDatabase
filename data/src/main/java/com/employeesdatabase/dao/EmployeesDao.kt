package com.employeesdatabase.dao

import com.employeesdatabase.AddressDbQueries
import com.employeesdatabase.EmployeesDbQueries
import com.employeesdatabase.models.EmployeeEntity

class EmployeesDao(
    private val employeesDbQueries: EmployeesDbQueries,
    private val addressDbQueries: AddressDbQueries
) {

    fun insertEmployee(employeeEntity: EmployeeEntity) {
        with(employeeEntity) {
            employeesDbQueries.insertOrReplace(
                firstName = firstName,
                lastName = lastName,
                age = age,
                gender = gender
            )

            addressess.forEach { address ->
                with(address) {
                    addressDbQueries.insertOrReplace(
                        street = street,
                        city = city,
                        zip = zip,
                        country = country,
                        employeeId = employeesDbQueries.selectByAllFields(firstName, lastName, age, gender).executeAsOne().id.toInt()
                    )
                }
            }
        }
    }
}