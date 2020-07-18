package com.employeesdatabase.dataSource

import com.employeesdatabase.common.UNINITIALIZED
import com.employeesdatabase.dao.EmployeesDao
import com.employeesdatabase.models.AddressEntity
import com.employeesdatabase.models.EmployeeEntity
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    private lateinit var dataSource: LocalDataSource
    private val employeesDao: EmployeesDao = mockk(relaxed = true)
    private val mockedEmployee: EmployeeEntity = mockk(relaxed = true)
    private val mockedAddress: AddressEntity = mockk(relaxed = true)

    @Before
    fun setup() {
        dataSource = LocalDataSource(employeesDao = employeesDao)
    }

    @Test
    fun `When getAllEmployees invoked call employeesDao getAllEmployees method`() {
        dataSource.getAllEmployees()

        verify { employeesDao.getAllEmployees() }
    }

    @Test
    fun `When getEmployeeById invoked call employeesDao getEmployeeById method`() {
        dataSource.getEmployeeById(mockedEmployee.id ?: UNINITIALIZED)

        verify { employeesDao.getEmployeeById(mockedEmployee.id ?: UNINITIALIZED) }
    }

    @Test
    fun `When insertEmployee invoked call employeesDao insertEmployee method`() {
        dataSource.insertEmployee(mockedEmployee)

        verify { employeesDao.insertEmployee(mockedEmployee) }
    }

    @Test
    fun `When updateEmployee invoked call employeesDao updateEmployee method`() {
        dataSource.updateEmployee(mockedEmployee)

        verify { employeesDao.updateEmployee(mockedEmployee) }
    }

    @Test
    fun `When deleteEmployee invoked call employeesDao deleteEmployee method`() {
        dataSource.deleteEmployee(mockedEmployee)

        verify { employeesDao.deleteEmployee(mockedEmployee) }
    }

    @Test
    fun `When deleteAddress invoked call employeesDao deleteAddress method`() {
        dataSource.deleteAddress(mockedAddress)

        verify { employeesDao.deleteAddress(mockedAddress) }
    }
}