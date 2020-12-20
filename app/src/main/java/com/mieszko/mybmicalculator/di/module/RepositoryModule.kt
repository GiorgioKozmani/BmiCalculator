package com.mieszko.mybmicalculator.di.module

import com.mieszko.mybmicalculator.data.repository.BmiRepositoryImpl
import com.mieszko.mybmicalculator.domain.repository.BmiRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single<BmiRepository> { BmiRepositoryImpl(get()) }
}