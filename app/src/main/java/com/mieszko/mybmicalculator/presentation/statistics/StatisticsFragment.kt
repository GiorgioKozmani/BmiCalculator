package com.mieszko.mybmicalculator.presentation.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mieszko.mybmicalculator.R
import com.mieszko.mybmicalculator.databinding.StatisticsFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class StatisticsFragment : Fragment() {
    private val viewModel by viewModel<StatisticsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<StatisticsFragmentBinding>(
            inflater,
            R.layout.statistics_fragment,
            container,
            false
        ).apply {
            statisticsViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchStatistics()
    }

}