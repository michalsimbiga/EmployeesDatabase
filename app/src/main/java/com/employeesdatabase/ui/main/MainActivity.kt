package com.employeesdatabase.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.employeesdatabase.R
import com.employeesdatabase.di.dataModule
import com.employeesdatabase.di.domainModule
import com.employeesdatabase.di.mainModule
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
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

    fun showSnackbar(@StringRes messageRes: Int, isShort: Boolean = true) =
        Snackbar.make(
            mainLayout,
            messageRes,
            if (isShort) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG
        ).show()

    override fun onDestroy() {
        unloadKoinModules(listOf(mainModule, domainModule, dataModule))

        super.onDestroy()
    }
}