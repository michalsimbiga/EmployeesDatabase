package com.employeesdatabase.dao

import com.employeesdatabase.*
import com.employeesdatabase.models.AddressEntity
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
                .selectByEmployeeId(employee.id)
                .executeAsList()
                .map(AddressDb::toEntity)

            employeeEntityList.add(employee.toEntity().copy(addressess = addressess))
        }

        return employeeEntityList
    }

    fun insertEmployee(employeeEntity: EmployeeEntity) {
        with(employeeEntity) {
            employeesDbQueries.insertOrReplace(
                id = if(id != UNINITIALIZED) id else null,
                firstName = firstName,
                lastName = lastName,
                age = age,
                gender = gender
            )

            val employeeId =
                employeesDbQueries.selectByAllFields(firstName, lastName, age, gender)
                    .executeAsOne().id

            addressess.forEach { address ->
                insertAddress(address, employeeId)
            }
        }
    }

    fun insertAddress(address: AddressEntity, employeeId: Long) = with(address) {
        addressDbQueries.insertOrReplace(
            id = if(id != UNINITIALIZED) id else null,
            street = street,
            city = city,
            zip = zip,
            country = country,
            employeeId = employeeId
        )
    }

    fun deleteAddress(address: AddressEntity) =
        addressDbQueries.deleteById(address.id ?: UNINITIALIZED)

    fun deleteEmployee(employee: EmployeeEntity) =
        employeesDbQueries.deleteEmployeeById(employee.id ?: UNINITIALIZED)
}