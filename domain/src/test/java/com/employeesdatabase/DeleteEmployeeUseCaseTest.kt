package com.employeesdatabase

import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Address
import com.employeesdatabase.models.Employee
import com.employeesdatabase.repository.EmployeesRepository
import com.employeesdatabase.useCase.DeleteAddressByIdUseCase
import com.employeesdatabase.useCase.DeleteEmployeeUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteEmployeeUseCaseTest {

    private lateinit var useCase: DeleteEmployeeUseCase
    private val repository: EmployeesRepository = mockk(relaxed = true)
    private val mockedEmployee: Employee = mockk(relaxed = true)

    @Before
    fun setup() {
        useCase = DeleteEmployeeUseCase(employeesRepository = repository)
    }

    @Test
    fun `When use case invoked call repository deleteEmployee method`() =
        runBlocking {
            coEvery { repository.deleteEmployee(any()) } returns Result.Success(Unit)

            useCase.invoke(DeleteEmployeeUseCase.Params(mockedEmployee))

            coVerify { repository.deleteEmployee(mockedEmployee) }
        }
}