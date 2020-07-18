package com.employeesdatabase

import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository
import com.employeesdatabase.useCase.InsertOrReplaceEmployeeUseCase
import com.employeesdatabase.useCase.UpdateEmployeeUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateEmployeeUseCaseTest {

    private lateinit var useCase: UpdateEmployeeUseCase
    private val repository: EmployeesRepository = mockk(relaxed = true)
    private val mockedEmployee: Employee = mockk(relaxed = true)

    @Before
    fun setup() {
        useCase = UpdateEmployeeUseCase(employeesRepository = repository)
    }

    @Test
    fun `When use case invoked call repository updateEmployee method`() =
        runBlocking {
            coEvery { repository.updateEmployee(any()) } returns Result.Success(Unit)

            useCase.invoke(UpdateEmployeeUseCase.Params(mockedEmployee))

            coVerify { repository.updateEmployee(mockedEmployee) }
        }
}