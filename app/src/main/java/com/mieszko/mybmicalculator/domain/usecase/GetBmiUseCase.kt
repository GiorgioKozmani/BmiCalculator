package com.mieszko.mybmicalculator.domain.usecase

import com.mieszko.mybmicalculator.domain.model.BmiInput
import com.mieszko.mybmicalculator.domain.repository.BmiRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface GetBmiUseCase {
    fun getBmi(userInput: BmiInput): Single<String>
}

class GetBmiUseCaseImpl(private val bmiRepository: BmiRepository) : GetBmiUseCase {

    override fun getBmi(userInput: BmiInput): Single<String> {
        return bmiRepository.getBmi(userInput)
            .subscribeOn(Schedulers.io())
    }
}