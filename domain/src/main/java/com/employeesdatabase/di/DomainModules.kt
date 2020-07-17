package com.employeesdatabase.di

import com.employeesdatabase.useCase.DeleteAddressByIdUseCase
import com.employeesdatabase.useCase.DeleteEmployeeUseCase
import com.employeesdatabase.useCase.GetAllEmployeesUseCase
import com.employeesdatabase.useCase.InsertOrReplaceEmployeeUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { InsertOrReplaceEmployeeUseCase(employeesRepository = get()) }

    factory { GetAllEmployeesUseCase(employeesRepository = get()) }

    factory { DeleteEmployeeUseCase(employeesRepository = get()) }

    factory { DeleteAddressByIdUseCase(employeesRepository = get()) }
}