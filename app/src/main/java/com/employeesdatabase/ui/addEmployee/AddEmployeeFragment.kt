package com.employeesdatabase.ui.addEmployee

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
import kotlinx.android.synthetic.main.fragment_add_employee.*
import kotlinx.android.synthetic.main.fragment_add_employee.view.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AddEmployeeFragment : BaseFragment() {

    private val epoxyController by lazy { AddEmployeeEpoxyController() }

    private val viewModel: AddEmployeeViewModel by fragmentViewModel()

    override fun onAttach(context: Context) {
        loadKoinModules(addFragmentViewModel)

        super.onAttach(context)
    }

    override fun invalidate() = withState(viewModel) { state ->
        if (state.addUserResult is Success) findNavController().navigate(R.id.action_edit_to_home)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_employee, container, false).apply {
        addEmployeeRecycler.setController(epoxyController)
        epoxyController.requestModelBuild()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addEmployeeButton.setOnClickListener {
            viewModel.addEmployee(epoxyController.getEmployee())
        }
    }

    override fun onDetach() {
        unloadKoinModules(addFragmentViewModel)

        super.onDetach()
    }

    override fun onDestroyView() {
        addEmployeeButton.setOnClickListener(null)

        super.onDestroyView()
    }
}
