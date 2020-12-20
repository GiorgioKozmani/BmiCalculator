package com.mieszko.mybmicalculator.domain.repository

import com.mieszko.mybmicalculator.domain.model.BmiInput
import io.reactivex.Single

interface BmiRepository {
    fun getBmi(userInput: BmiInput): Single<String>
}