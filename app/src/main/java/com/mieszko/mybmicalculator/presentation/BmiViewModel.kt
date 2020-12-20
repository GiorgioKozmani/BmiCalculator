package com.mieszko.mybmicalculator.presentation

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mieszko.mybmicalculator.domain.model.BmiInput
import com.mieszko.mybmicalculator.domain.usecase.GetBmiUseCase
import io.reactivex.disposables.CompositeDisposable

class BmiViewModel(
    private val getBmiUseCase: GetBmiUseCase
) : ViewModel() {
    private val disposablesBag = CompositeDisposable()

    var inputWeight: Int = 0
    var inputHeight: Int = 0
    var bmiText: ObservableField<String> =
        ObservableField("Input your height and weight and click CHECK BMI")

    fun performBmiCheck() {
        // cancel cancel not finished calls
        disposablesBag.clear()
        disposablesBag.add(
            getBmiUseCase.getBmi(BmiInput(inputHeight, inputWeight))
                .subscribe(
                    { serverResponse -> bmiText.set(serverResponse) },
                    { Log.d("testError", it.toString()) }
                )
        )
    }

    override fun onCleared() {
        disposablesBag.dispose()
        super.onCleared()
    }
}