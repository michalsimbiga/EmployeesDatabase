package com.employeesdatabase

class DatabaseImpl(private val database: Lazy<MyDatabase>) : Database {

    private val myDatabase by lazy { database.value }

    override fun employeeQueries(): EmployeesDbQueries = myDatabase.employeesDbQueries

    override fun addressQueries(): AddressDbQueries = myDatabase.addressDbQueries
}
