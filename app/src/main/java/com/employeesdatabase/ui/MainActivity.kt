package com.employeesdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.employeesdatabase.R
import com.employeesdatabase.di.dataModule
import com.employeesdatabase.di.domainModule
import com.employeesdatabase.di.mainModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MainActivity : AppCompatActivity() {

    init {
        loadKoinModules(listOf(mainModule, domainModule, dataModule))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        unloadKoinModules(listOf(mainModule, domainModule, dataModule))

        super.onDestroy()
    }
}