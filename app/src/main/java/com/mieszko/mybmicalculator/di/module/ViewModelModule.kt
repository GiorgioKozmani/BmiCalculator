package com.mieszko.mybmicalculator.di.module

import com.mieszko.mybmicalculator.presentation.calculator.CalculatorViewModel
import com.mieszko.mybmicalculator.presentation.statistics.StatisticsViewModel
import com.mieszko.mybmicalculator.common.DisposablesBag
import com.mieszko.mybmicalculator.common.DisposablesBagImpl
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { CalculatorViewModel(get(), get()) }
    viewModel { StatisticsViewModel(get(), get()) }
    factory<DisposablesBag> { DisposablesBagImpl(CompositeDisposable()) }
}