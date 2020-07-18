package com.employeesdatabase.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.employeesdatabase.R
import com.employeesdatabase.common.BaseFragment
import com.employeesdatabase.di.homeFragmentViewModel
import com.employeesdatabase.models.EmployeeItem
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by fragmentViewModel()

    private val epoxyController = HomeEpoxyController(
        onDeleteEmployeeCallback = ::onDeleteEmployee,
        onEditEmployeeCallback = ::onEditEmployee
    )

    private fun onDeleteEmployee(employee: EmployeeItem) {
        viewModel.deleteEmployee(employee)
    }

    private fun onEditEmployee(employee: EmployeeItem) {
        val action = HomeFragmentDirections.actionHomeToEdit(employee)
        findNavController().navigate(action)
    }

    override fun invalidate() = withState(viewModel) { state ->
        if (state.listOfEmployees is Success) epoxyController.setData(state.listOfEmployees.invoke())
    }

    override fun onAttach(context: Context) {
        loadKoinModules(homeFragmentViewModel)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false).apply {
        homeRecycler.setController(epoxyController)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllEmployees()

        addButton.setOnClickListener { findNavController().navigate(R.id.addEmployeeFragment) }
    }

    override fun onDetach() {
        unloadKoinModules(homeFragmentViewModel)

        super.onDetach()
    }

    override fun onDestroy() {
        epoxyController.clearCallbacks()
        unloadKoinModules(homeFragmentViewModel)

        super.onDestroy()
    }

    override fun onDestroyView() {
        onRecyclerViewDetached(homeRecycler)
        addButton.setOnClickListener(null)

        super.onDestroyView()
    }
}
