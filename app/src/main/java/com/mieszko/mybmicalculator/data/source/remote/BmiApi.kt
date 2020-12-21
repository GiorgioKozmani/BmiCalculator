package com.mieszko.mybmicalculator.data.source.remote

import com.mieszko.mybmicalculator.data.model.BmiStatisticsDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BmiApi {

    @GET("calc/{height}/{weight}")
    fun getBmi(@Path("height") height: Int, @Path("weight") weight: Int): Single<String>

    @GET("info")
    fun getStatistics(): Single<BmiStatisticsDTO>
}