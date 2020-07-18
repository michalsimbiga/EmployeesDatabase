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
            street = street,
            city = city,
            zip = zip,
            country = country,
            employeeId = employeeId
        )
    }

    fun updateEmployee(employee: EmployeeEntity) {
        with(employee) {
            employeesDbQueries.update(
                id = id,
                firstName = firstName,
                lastName = lastName,
                age = age,
                gender = gender
            )

            addressess.forEach { address -> insertAddress(address, id ?: return) }
        }
    }

    fun updateAddress(address: AddressEntity, employeeId: Long) = with(address) {
        addressDbQueries.update(
            id = id,
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