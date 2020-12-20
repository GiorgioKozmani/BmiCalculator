package com.mieszko.mybmicalculator.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mieszko.mybmicalculator.R
import com.mieszko.mybmicalculator.databinding.BmiFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class BmiFragment : Fragment() {
    private val viewModel by viewModel<BmiViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<BmiFragmentBinding>(
            inflater,
            R.layout.bmi_fragment,
            container,
            false
        ).apply {
            bmiViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

}