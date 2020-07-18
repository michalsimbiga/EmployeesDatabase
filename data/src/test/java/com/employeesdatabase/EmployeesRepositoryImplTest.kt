package com.employeesdatabase

import com.employeesdatabase.dataSource.LocalDataSource
import com.employeesdatabase.models.Address
import com.employeesdatabase.models.Employee
import com.employeesdatabase.models.EmployeeEntity
import com.employeesdatabase.models.toEntity
import com.employeesdatabase.repository.EmployeesRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class EmployeesRepositoryImplTest {

    private lateinit var repository: EmployeesRepositoryImpl

    private val localDataSource: LocalDataSource = mockk(relaxed = true)

    private val mockedEmployeeList: List<EmployeeEntity> = mockk(relaxed = true)
    private val mockedEmployee: Employee = mockk(relaxed = true)
    private val mockedAddress: Address = mockk(relaxed = true)

    @Before
    fun setup() {
        repository = EmployeesRepositoryImpl(localDataSource = localDataSource)
    }

    @Test
    fun `When getAllEmployees invoked call local data source getAllEmployees method`() =
        runBlocking {

            coEvery { localDataSource.getAllEmployees() } returns mockedEmployeeList

            repository.getAllEmployees()

            coVerify { localDataSource.getAllEmployees() }
        }

    @Test
    fun `When getEmployeeById invoked call local data source getEmployeeById method`() =
        runBlocking {
            coEvery { localDataSource.getEmployeeById(any()) } returns mockedEmployee.toEntity()

            repository.getEmployeeById(mockedEmployee.id)

            coVerify { localDataSource.getEmployeeById(mockedEmployee.id) }
        }

    @Test
    fun `When insertEmployee invoked call local data source insertEmployee method`() =
        runBlocking {

            coEvery { localDataSource.insertEmployee(any()) } returns Unit

            repository.insertEmployee(mockedEmployee)

            coVerify { localDataSource.insertEmployee(mockedEmployee.toEntity()) }
        }

    @Test
    fun `When updateEmployee invoked call local data source updateEmployee method`() =
        runBlocking {

            coEvery { localDataSource.updateEmployee(any()) } returns Unit

            repository.updateEmployee(mockedEmployee)

            coVerify { localDataSource.updateEmployee(mockedEmployee.toEntity()) }
        }

    @Test
    fun `When deleteEmployee invoked call local data source deleteEmployee method`() =
        runBlocking {

            coEvery { localDataSource.deleteEmployee(any()) } returns Unit

            repository.deleteEmployee(mockedEmployee)

            coVerify { localDataSource.deleteEmployee(mockedEmployee.toEntity()) }
        }

    @Test
    fun `When deleteAddress invoked call local data source getEmployeeById method`() =
        runBlocking {

            coEvery { localDataSource.deleteAddress(any()) } returns Unit

            repository.deleteAddress(mockedAddress)

            coVerify { localDataSource.deleteAddress(mockedAddress.toEntity()) }
        }
}