package com.employeesdatabase

import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository
import com.employeesdatabase.useCase.GetEmployeeByIdUseCase
import com.employeesdatabase.useCase.InsertOrReplaceEmployeeUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InsertOrReplaceEmployeeUseCaseTest {

    private lateinit var useCase: InsertOrReplaceEmployeeUseCase
    private val repository: EmployeesRepository = mockk(relaxed = true)
    private val mockedEmployee: Employee = mockk(relaxed = true)

    @Before
    fun setup() {
        useCase = InsertOrReplaceEmployeeUseCase(employeesRepository = repository)
    }

    @Test
    fun `When use case invoked call repository getEmployeeById method`() =
        runBlocking {
            coEvery { repository.insertEmployee(any()) } returns Result.Success(Unit)

            useCase.invoke(InsertOrReplaceEmployeeUseCase.Params(mockedEmployee))

            coVerify { repository.insertEmployee(mockedEmployee) }
        }
}