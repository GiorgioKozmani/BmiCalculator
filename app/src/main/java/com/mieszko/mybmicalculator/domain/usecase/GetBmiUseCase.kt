package com.mieszko.mybmicalculator.domain.usecase

import com.mieszko.mybmicalculator.domain.model.BmiInput
import com.mieszko.mybmicalculator.domain.repository.BmiRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface GetBmiUseCase {
    fun getBmi(userInput: BmiInput): Single<String>
}

class GetBmiUseCaseImpl(private val soundRepository: BmiRepository) : GetBmiUseCase {

    override fun getBmi(userInput: BmiInput): Single<String> {
        return soundRepository.getBmi(userInput)
            .subscribeOn(Schedulers.io())
    }
}