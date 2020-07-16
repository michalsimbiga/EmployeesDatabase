package com.employeesdatabase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.employeesdatabase.R
import com.employeesdatabase.common.BaseFragment
import com.employeesdatabase.doNothing
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun invalidate() = doNothing

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener { findNavController().navigate(R.id.addEmployeeFragment) }
    }

    override fun onDestroyView() {
        addButton.setOnClickListener(null)

        super.onDestroyView()
    }
}