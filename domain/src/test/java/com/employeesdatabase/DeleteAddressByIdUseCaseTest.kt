package com.employeesdatabase

import com.employeesdatabase.common.Result
import com.employeesdatabase.models.Address
import com.employeesdatabase.repository.EmployeesRepository
import com.employeesdatabase.useCase.DeleteAddressByIdUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteAddressByIdUseCaseTest {

    private lateinit var useCase: DeleteAddressByIdUseCase
    private val repository: EmployeesRepository = mockk(relaxed = true)
    private val mockedAddress: Address = mockk(relaxed = true)

    @Before
    fun setup() {
        useCase = DeleteAddressByIdUseCase(employeesRepository = repository)
    }

    @Test
    fun `When use case invoked call repository deleteAddress method`() =
        runBlocking {
            coEvery { repository.deleteAddress(any()) } returns Result.Success(Unit)

            useCase.invoke(DeleteAddressByIdUseCase.Params(mockedAddress))

            coVerify { repository.deleteAddress(mockedAddress) }
        }
}