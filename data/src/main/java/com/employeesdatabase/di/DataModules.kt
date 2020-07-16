package com.employeesdatabase.di

import com.employeesdatabase.Database
import com.employeesdatabase.dao.EmployeesDao
import com.employeesdatabase.dataSource.LocalDataSource
import com.employeesdatabase.repository.EmployeesRepository
import com.employeesdatabase.repository.EmployeesRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single { get<Database>().employeeQueries() }
    single { get<Database>().addressQueries() }

    single {
        EmployeesDao(
            employeesDbQueries = get(),
            addressDbQueries = get()
        )
    }

    single { LocalDataSource(employeesDao = get()) }

    single<EmployeesRepository> { EmployeesRepositoryImpl(localDataSource = get()) }
}