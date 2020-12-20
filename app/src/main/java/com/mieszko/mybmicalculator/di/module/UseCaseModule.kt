package com.mieszko.mybmicalculator.di.module

import com.mieszko.mybmicalculator.domain.usecase.GetBmiUseCase
import com.mieszko.mybmicalculator.domain.usecase.GetBmiUseCaseImpl
import org.koin.dsl.module

val UseCaseModule = module {
    single<GetBmiUseCase> { GetBmiUseCaseImpl(get()) }
}