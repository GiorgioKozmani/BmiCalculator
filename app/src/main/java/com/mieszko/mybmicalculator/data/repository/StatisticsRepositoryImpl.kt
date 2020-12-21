package com.mieszko.mybmicalculator.data.repository

import com.mieszko.mybmicalculator.data.mapper.BmiStatisticsMapper
import com.mieszko.mybmicalculator.data.source.remote.BmiApi
import com.mieszko.mybmicalculator.domain.model.BmiStatistics
import com.mieszko.mybmicalculator.domain.repository.StatisticsRepository
import io.reactivex.Single

class StatisticsRepositoryImpl(
    private val bmiApi: BmiApi,
    private val mapper: BmiStatisticsMapper
) : StatisticsRepository {

    override fun getStatistics(): Single<BmiStatistics> {
        return bmiApi.getStatistics().map { mapper.dtoToDomainModel(it) }
    }
}