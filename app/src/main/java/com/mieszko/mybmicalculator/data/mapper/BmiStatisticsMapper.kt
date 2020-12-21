package com.mieszko.mybmicalculator.data.mapper

import com.mieszko.mybmicalculator.data.model.BmiStatisticsDTO
import com.mieszko.mybmicalculator.domain.model.BmiStatistics

interface BmiStatisticsMapper {
    fun dtoToDomainModel(dto: BmiStatisticsDTO): BmiStatistics
}

class BmiStatisticsMapperImpl : BmiStatisticsMapper {

    override fun dtoToDomainModel(dto: BmiStatisticsDTO): BmiStatistics {
        val statsRows = mutableMapOf<String, String>()

        dto.total?.let { statsRows.put("Total", it.toString()) }
        dto.average?.let { statsRows.put("Average BMI", it.toString()) }
        dto.errors?.let { statsRows.put("Errors", it.toString()) }
        dto.calculations?.forEach { statsRows[it.key] = it.value.toString() }
        dto.latestCalculation?.let { statsRows.put("Latest calculation", it) }

        return BmiStatistics(statsRows)
    }
}
