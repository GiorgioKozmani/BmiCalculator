package com.mieszko.mybmicalculator.data.mapper

import com.mieszko.mybmicalculator.data.model.BmiStatisticsDTO
import com.mieszko.mybmicalculator.domain.model.BmiStatistics
import org.junit.Assert
import org.junit.Test

class BmiStatisticsMapperImplTest {

    @Test
    fun `mapper ignores null fields`() {
        val testDto = BmiStatisticsDTO()

        Assert.assertEquals(BmiStatistics(), BmiStatisticsMapperImpl().dtoToDomainModel(testDto))
    }

    @Test
    fun `mapper maps dto to domain model`() {
        val testDto = BmiStatisticsDTO(
            total = 1,
            calculations = mapOf(Pair("count1", 1), Pair("count2", 2)),
            errors = 3,
            average = 4.5f,
            latestCalculation = "testLatest"
        )

        val mapped = BmiStatisticsMapperImpl().dtoToDomainModel(testDto)

        // usecase returns repository response
        Assert.assertEquals(
            BmiStatistics(
                mapOf(
                    Pair("Total", "1"),
                    Pair("Average BMI", "4.5"),
                    Pair("Errors", "3"),
                    Pair("count1", "1"),
                    Pair("count2", "2"),
                    Pair("Latest calculation", "testLatest")
                )
            ), mapped
        )
    }

}