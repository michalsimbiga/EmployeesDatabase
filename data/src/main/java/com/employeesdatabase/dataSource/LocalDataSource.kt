package com.employeesdatabase.dataSource

import com.employeesdatabase.dao.EmployeesDao
import com.employeesdatabase.models.EmployeeEntity

class LocalDataSource(private val employeesDao: EmployeesDao) {

    fun insertEmployee(employeeEntity: EmployeeEntity) = employeesDao.insertEmployee(employeeEntity)
    fun getAllEmployees() = employeesDao.getAllEmployees()
}