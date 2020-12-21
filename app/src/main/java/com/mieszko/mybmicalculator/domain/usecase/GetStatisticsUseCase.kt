package com.mieszko.mybmicalculator.domain.usecase

import com.mieszko.mybmicalculator.domain.model.BmiStatistics
import com.mieszko.mybmicalculator.domain.repository.StatisticsRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface GetStatisticsUseCase {
    fun getStatistics(): Single<BmiStatistics>
}

class GetStatisticsRowsUseCaseImpl(private val statisticsRepository: StatisticsRepository) :
    GetStatisticsUseCase {

    override fun getStatistics(): Single<BmiStatistics> {
        return statisticsRepository.getStatistics()
            .subscribeOn(Schedulers.io())
    }
}