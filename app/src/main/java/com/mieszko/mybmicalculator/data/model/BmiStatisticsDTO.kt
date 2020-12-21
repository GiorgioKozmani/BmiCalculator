package com.mieszko.mybmicalculator.data.model

import com.google.gson.annotations.SerializedName

data class BmiStatisticsDTO(
    // we cannot assume that these fields are going to be present, therefore they're all nullable
    @SerializedName("Total")
    var total: Int? = null,

    @SerializedName("Calculations")
    var calculations: Map<String, Int>? = null,

    @SerializedName("Errors")
    var errors: Int? = null,

    @SerializedName("Average")
    var average: Float? = null,

    @SerializedName("LatestCalculation")
    var latestCalculation: String? = null
)