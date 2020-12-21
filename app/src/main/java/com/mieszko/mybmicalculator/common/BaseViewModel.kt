package com.mieszko.mybmicalculator.common

import androidx.lifecycle.ViewModel

abstract class BaseViewModel(val disposablesBag: DisposablesBag) : ViewModel() {

    override fun onCleared() {
        disposablesBag.dispose()
        super.onCleared()
    }
}