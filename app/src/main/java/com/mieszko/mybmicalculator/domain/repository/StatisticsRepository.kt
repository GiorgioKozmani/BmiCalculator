package com.mieszko.mybmicalculator.domain.repository

import com.mieszko.mybmicalculator.domain.model.BmiStatistics
import io.reactivex.Single

interface StatisticsRepository {
    fun getStatistics(): Single<BmiStatistics>
}