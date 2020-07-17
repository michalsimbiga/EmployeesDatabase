package com.employeesdatabase.dao

import com.employeesdatabase.AddressDb
import com.employeesdatabase.AddressDbQueries
import com.employeesdatabase.EmployeeDb
import com.employeesdatabase.EmployeesDbQueries
import com.employeesdatabase.models.Employee
import com.employeesdatabase.models.EmployeeEntity
import com.employeesdatabase.models.toEntity

class EmployeesDao(
    private val employeesDbQueries: EmployeesDbQueries,
    private val addressDbQueries: AddressDbQueries
) {

    fun getAllEmployees(): List<EmployeeEntity> {
        val employeeEntityList = mutableListOf<EmployeeEntity>()

        employeesDbQueries.selectAll().executeAsList().forEach { employee ->
            val addressess = addressDbQueries
                .selectByEmployeeId(employee.id.toInt())
                .executeAsList()
                .map(AddressDb::toEntity)

            employeeEntityList.add(employee.toEntity().copy(addressess = addressess))
        }

        return employeeEntityList
    }

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
                        employeeId = employeesDbQueries.selectByAllFields(
                            firstName,
                            lastName,
                            age,
                            gender
                        ).executeAsOne().id.toInt()
                    )
                }
            }
        }
    }

    fun deleteEmployee(employee: EmployeeEntity) =
        employeesDbQueries.deleteEmployeeById(employee.id.toLong())
}