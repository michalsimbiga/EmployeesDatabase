package com.employeesdatabase.ui.addEmployee

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.employeesdatabase.R
import com.employeesdatabase.common.BaseFragment
import com.employeesdatabase.di.addFragmentViewModel
import com.employeesdatabase.doNothing
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AddEmployeeFragment : BaseFragment() {

    override fun onAttach(context: Context) {
        loadKoinModules(addFragmentViewModel)

        super.onAttach(context)
    }

    override fun invalidate() = doNothing

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_employee, container, false)

    override fun onDetach() {
        unloadKoinModules(addFragmentViewModel)

        super.onDetach()
    }
}
