package com.employeesdatabase.ui.edit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.employeesdatabase.R
import com.employeesdatabase.common.BaseFragment
import com.employeesdatabase.di.addFragmentViewModel
import com.employeesdatabase.models.AddressItem
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class EditFragment : BaseFragment() {

    private val epoxyController by lazy {
        EditEpoxyController(onDeleteAddressCallback = ::deleteAddress)
    }

    private val viewModel: EditViewModel by fragmentViewModel()
    private val args: EditFragmentArgs by navArgs()

    private fun deleteAddress(address: AddressItem) {
        viewModel.deleteAddress(address)
    }

    override fun onAttach(context: Context) {
        loadKoinModules(addFragmentViewModel)

        super.onAttach(context)
    }

    override fun invalidate() = withState(viewModel) { state ->
        if (state.navigateForward is Success) findNavController().navigate(R.id.action_edit_to_home)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_edit, container, false).apply {
        addEmployeeRecycler.setController(epoxyController)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.employee?.let { employee ->
            with(viewModel) {
                setEditMode()
                epoxyController.setNewEmployee(employee)
            }
        }

        epoxyController.requestModelBuild()

        viewModel.asyncSubscribe(
            viewLifecycleOwner,
            EditViewState::userOperationResult,
            deliveryMode = UniqueOnly(subscriptionId = USER_OPERATIONS_KEY),
            onSuccess = { showSnackbar(R.string.operation_successful) },
            onFail = { showSnackbar(R.string.operation_unsuccessful) }
        )

        addEmployeeButton.setOnClickListener { viewModel.editOrAddEmployee(epoxyController.getEmployee()) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        epoxyController.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onDetach() {
        unloadKoinModules(addFragmentViewModel)

        super.onDetach()
    }

    override fun onDestroyView() {
        addEmployeeButton.setOnClickListener(null)
        epoxyController.clearCallbacks()

        super.onDestroyView()
    }

    companion object {
        private const val USER_OPERATIONS_KEY = "UserOperationsKey"
    }
}
