package com.employeesdatabase.di

import androidx.sqlite.db.SupportSQLiteDatabase
import com.employeesdatabase.Database
import com.employeesdatabase.DatabaseImpl
import com.employeesdatabase.MyDatabase
import com.employeesdatabase.ui.edit.EditViewModel
import com.employeesdatabase.ui.edit.EditViewState
import com.employeesdatabase.ui.home.HomeViewModel
import com.employeesdatabase.ui.home.HomeViewState
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DB_NAME = "EMPLOYEES.DB"

val mainModule = module {

    single {
        AndroidSqliteDriver(
            schema = MyDatabase.Schema,
            context = androidContext(),
            name = DB_NAME,
            callback = object : AndroidSqliteDriver.Callback(MyDatabase.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.execSQL("PRAGMA foreign_keys=ON;")
                }
            }
        ) as SqlDriver
    }

    single {
        DatabaseImpl(database = lazy { get<MyDatabase>() }) as Database
    }

    single {
        MyDatabase(
            driver = get()
        )
    }
}

val addFragmentViewModel = module {
    viewModel { (state: EditViewState) ->
        EditViewModel(
            state = state,
            insertOrReplaceEmployeeUseCase = get(),
            updateEmployeeUseCase = get(),
            getEmployeeByIdUseCase = get(),
            deleteAddressByIdUseCase = get()
        )
    }
}

val homeFragmentViewModel = module {
    viewModel { (state: HomeViewState) ->
        HomeViewModel(
            state = state,
            getAllEmployeesUseCase = get(),
            deleteEmployeeUseCase = get()
        )
    }
}
