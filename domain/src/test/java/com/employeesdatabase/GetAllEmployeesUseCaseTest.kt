package com.employeesdatabase

import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository
import com.employeesdatabase.useCase.GetAllEmployeesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllEmployeesUseCaseTest {

    private lateinit var useCase: GetAllEmployeesUseCase
    private val repository: EmployeesRepository = mockk(relaxed = true)
    private val mockedListOfEmployees: List<Employee> = mockk(relaxed = true)

    @Before
    fun setup() {
        useCase = GetAllEmployeesUseCase(employeesRepository = repository)
    }

    @Test
    fun `When use case invoked call repository getAllEmployees method`() =
        runBlocking {
            coEvery { repository.getAllEmployees() } returns Result.Success(mockedListOfEmployees)

            useCase.invoke()

            coVerify { repository.getAllEmployees() }
        }
}