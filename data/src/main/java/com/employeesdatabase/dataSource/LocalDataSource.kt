package com.employeesdatabase.dataSource

import com.employeesdatabase.dao.EmployeesDao
import com.employeesdatabase.models.AddressEntity
import com.employeesdatabase.models.EmployeeEntity

class LocalDataSource(private val employeesDao: EmployeesDao) {

    fun insertEmployee(employee: EmployeeEntity) = employeesDao.insertEmployee(employee)
//    fun editEmployee(employee: EmployeeEntity) = employeesDao.
    fun getAllEmployees() = employeesDao.getAllEmployees()
    fun deleteEmployee(employee: EmployeeEntity) = employeesDao.deleteEmployee(employee)
    fun deleteAddress(address: AddressEntity) = employeesDao.deleteAddress(address)
}