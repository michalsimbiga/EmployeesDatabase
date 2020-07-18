package com.employeesdatabase

import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Address
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository
import com.employeesdatabase.useCase.DeleteAddressByIdUseCase
import com.employeesdatabase.useCase.GetEmployeeByIdUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetEmployeeByIdUseCaseTest {

    private lateinit var useCase: GetEmployeeByIdUseCase
    private val repository: EmployeesRepository = mockk(relaxed = true)
    private val mockedEmployee: Employee = mockk(relaxed = true)

    @Before
    fun setup() {
        useCase = GetEmployeeByIdUseCase(employeesRepository = repository)
    }

    @Test
    fun `When use case invoked call repository getEmployeeById method`() =
        runBlocking {
            coEvery { repository.getEmployeeById(any()) } returns Result.Success(mockedEmployee)

            useCase.invoke(GetEmployeeByIdUseCase.Params(mockedEmployee.id))

            coVerify { repository.getEmployeeById(mockedEmployee.id) }
        }
}