package com.mieszko.mybmicalculator.presentation.calculator

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mieszko.mybmicalculator.common.BaseViewModel
import com.mieszko.mybmicalculator.common.DisposablesBag
import com.mieszko.mybmicalculator.common.LiveDataEvent
import com.mieszko.mybmicalculator.domain.model.BmiInput
import com.mieszko.mybmicalculator.domain.usecase.GetBmiUseCase

class CalculatorViewModel(
    private val getBmiUseCase: GetBmiUseCase,
    disposablesBag: DisposablesBag
) : BaseViewModel(disposablesBag) {
    private val newDestination: MutableLiveData<LiveDataEvent<Int>> = MutableLiveData()

    var inputWeight: Int = 0
    var inputHeight: Int = 0
    var bmiText: ObservableField<String> =
        ObservableField("Input your height and weight and click CHECK BMI")

    fun getNewDestination(): LiveData<LiveDataEvent<Int>> = newDestination

    fun setNewDestination(destinationId: Int) {
        newDestination.value = LiveDataEvent(destinationId)
    }

    fun performBmiCheck() {
        disposablesBag.run {
            // cancel cancel not finished calls
            clear()
            add(getBmiUseCase.getBmi(BmiInput(inputHeight, inputWeight))
                .subscribe(
                    { serverResponse -> bmiText.set(serverResponse) },
                    {
                        // ignore error
                    }
                )
            )
        }
    }
}