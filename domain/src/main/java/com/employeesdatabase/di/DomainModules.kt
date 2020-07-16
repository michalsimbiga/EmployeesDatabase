package com.employeesdatabase.di

import com.employeesdatabase.useCase.InsertOrReplaceEmployeeUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { InsertOrReplaceEmployeeUseCase(employeesRepository = get()) }
}