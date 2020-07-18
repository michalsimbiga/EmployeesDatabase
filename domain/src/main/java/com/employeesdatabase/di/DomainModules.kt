package com.employeesdatabase.di

import com.employeesdatabase.useCase.*
import org.koin.dsl.module

val domainModule = module {

    factory { InsertOrReplaceEmployeeUseCase(employeesRepository = get()) }

    factory { GetEmployeeByIdUseCase(employeesRepository = get()) }

    factory { GetAllEmployeesUseCase(employeesRepository = get()) }

    factory { DeleteEmployeeUseCase(employeesRepository = get()) }

    factory { DeleteAddressByIdUseCase(employeesRepository = get()) }

    factory { UpdateEmployeeUseCase(employeesRepository = get()) }
}