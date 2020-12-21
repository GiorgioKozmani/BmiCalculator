package com.mieszko.mybmicalculator.data.repository

import com.mieszko.mybmicalculator.data.mapper.BmiStatisticsMapper
import com.mieszko.mybmicalculator.data.model.BmiStatisticsDTO
import com.mieszko.mybmicalculator.data.source.remote.BmiApi
import com.mieszko.mybmicalculator.di.module.MapperModule
import com.mieszko.mybmicalculator.di.module.NetworkModule
import com.mieszko.mybmicalculator.domain.model.BmiStatistics
import io.reactivex.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito

class StatisticsRepositoryImplTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(NetworkModule, MapperModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `getStatistics calls server and returns mapped response`() {
        val mockStatisticsDTO = BmiStatisticsDTO()
        val mockMappedStatistics = BmiStatistics()

        val testApi = declareMock<BmiApi> {
            given(getStatistics())
                .willReturn(Single.just(mockStatisticsDTO))
        }

        val testMapper = declareMock<BmiStatisticsMapper> {
            given(dtoToDomainModel(mockStatisticsDTO))
                .willReturn(mockMappedStatistics)
        }

        // testing here could get improved by injecting schedulers, but it's too much for this project
        val repositoryResponse =
            StatisticsRepositoryImpl(testApi, testMapper).getStatistics().blockingGet()

        // api gets called with right arguments
        Mockito.verify(testApi).getStatistics()
        Mockito.verify(testMapper).dtoToDomainModel(mockStatisticsDTO)
        // repository returns server response
        Assert.assertEquals(mockMappedStatistics, repositoryResponse)
    }
}