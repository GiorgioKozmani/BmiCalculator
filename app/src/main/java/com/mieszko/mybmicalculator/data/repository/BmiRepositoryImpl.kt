package com.mieszko.mybmicalculator.data.repository

import com.mieszko.mybmicalculator.data.source.remote.BmiApi
import com.mieszko.mybmicalculator.domain.model.BmiInput
import com.mieszko.mybmicalculator.domain.repository.BmiRepository
import io.reactivex.Single

class BmiRepositoryImpl(private val bmiApi: BmiApi) : BmiRepository {

    override fun getBmi(userInput: BmiInput): Single<String> {
        return bmiApi.getBmi(userInput.height, userInput.weight)
    }
}