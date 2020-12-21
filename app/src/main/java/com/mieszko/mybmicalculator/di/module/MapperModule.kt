package com.mieszko.mybmicalculator.di.module

import com.mieszko.mybmicalculator.data.mapper.BmiStatisticsMapper
import com.mieszko.mybmicalculator.data.mapper.BmiStatisticsMapperImpl
import org.koin.dsl.module

val MapperModule = module {
    single<BmiStatisticsMapper> { BmiStatisticsMapperImpl() }
}