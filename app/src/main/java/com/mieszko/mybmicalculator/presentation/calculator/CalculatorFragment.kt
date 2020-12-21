package com.mieszko.mybmicalculator.presentation.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mieszko.mybmicalculator.R
import com.mieszko.mybmicalculator.databinding.CalculatorFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CalculatorFragment : Fragment() {
    private val viewModel by viewModel<CalculatorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<CalculatorFragmentBinding>(
            inflater,
            R.layout.calculator_fragment,
            container,
            false
        ).apply {
            bmiViewModel = viewModel
            navDirectionToStatistics = CalculatorFragmentDirections.toStatisticsDesc()
            lifecycleOwner = viewLifecycleOwner
        }

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel
            .getNewDestination()
            .observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    // Only proceed if the event has never been handled
                    navigate(it)
                }
            })
    }

    private fun navigate(destId: Int) {
        findNavController().navigate(destId)
    }
}