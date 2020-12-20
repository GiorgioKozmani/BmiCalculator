package com.mieszko.mybmicalculator.di.module

import com.mieszko.mybmicalculator.presentation.BmiViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { BmiViewModel(get()) }
}