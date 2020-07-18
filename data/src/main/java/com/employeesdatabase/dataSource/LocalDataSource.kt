package com.employeesdatabase.dataSource

import com.employeesdatabase.dao.EmployeesDao
import com.employeesdatabase.models.AddressEntity
import com.employeesdatabase.models.EmployeeEntity

class LocalDataSource(private val employeesDao: EmployeesDao) {

    fun getAllEmployees() = employeesDao.getAllEmployees()
    fun getEmployeeById(employeeId: Long) = employeesDao.getEmployeeById(employeeId)
    fun insertEmployee(employee: EmployeeEntity) = employeesDao.insertEmployee(employee)
    fun updateEmployee(employee: EmployeeEntity) = employeesDao.updateEmployee(employee)
    fun deleteEmployee(employee: EmployeeEntity) = employeesDao.deleteEmployee(employee)
    fun deleteAddress(address: AddressEntity) = employeesDao.deleteAddress(address)
}