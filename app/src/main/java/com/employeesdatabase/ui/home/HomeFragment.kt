package com.employeesdatabase.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.employeesdatabase.R
import com.employeesdatabase.common.BaseFragment
import com.employeesdatabase.di.addFragmentViewModel
import com.employeesdatabase.di.homeFragmentViewModel
import com.employeesdatabase.doNothing
import com.employeesdatabase.ui.addEmployee.AddEmployeeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by fragmentViewModel()

    override fun invalidate() = withState(viewModel) { state ->
        if(state.listOfEmployees is Success) {
            homeText.setText(state.listOfEmployees()?.joinToString(separator = "\n"))
        }
    }

    override fun onAttach(context: Context) {
        loadKoinModules(homeFragmentViewModel)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllEmployees()

        addButton.setOnClickListener { findNavController().navigate(R.id.addEmployeeFragment) }
    }

    override fun onDetach() {
        unloadKoinModules(homeFragmentViewModel)

        super.onDetach()
    }

    override fun onDestroyView() {
        addButton.setOnClickListener(null)

        super.onDestroyView()
    }
}