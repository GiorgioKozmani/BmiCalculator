package com.mieszko.mybmicalculator.di.module

import com.mieszko.mybmicalculator.data.repository.BmiRepositoryImpl
import com.mieszko.mybmicalculator.data.repository.StatisticsRepositoryImpl
import com.mieszko.mybmicalculator.domain.repository.BmiRepository
import com.mieszko.mybmicalculator.domain.repository.StatisticsRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single<BmiRepository> { BmiRepositoryImpl(get()) }
    single<StatisticsRepository> { StatisticsRepositoryImpl(get(), get()) }
}