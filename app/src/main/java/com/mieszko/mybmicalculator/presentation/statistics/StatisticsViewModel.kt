package com.mieszko.mybmicalculator.presentation.statistics

import androidx.databinding.ObservableField
import com.mieszko.mybmicalculator.common.BaseViewModel
import com.mieszko.mybmicalculator.common.DisposablesBag
import com.mieszko.mybmicalculator.domain.model.BmiStatistics
import com.mieszko.mybmicalculator.domain.usecase.GetStatisticsUseCase

class StatisticsViewModel(
    private val getStatisticsRowsUseCase: GetStatisticsUseCase,
    disposablesBag: DisposablesBag
) : BaseViewModel(disposablesBag) {
    var statisticsData: ObservableField<BmiStatistics> =
        ObservableField(BmiStatistics())

    fun fetchStatistics() {
        disposablesBag.run {
            // cancel cancel not finished calls
            clear()
            add(getStatisticsRowsUseCase.getStatistics()
                .subscribe(
                    { statsRows -> statisticsData.set(statsRows) },
                    {
                        // ignore error
                    }
                )
            )
        }
    }
}