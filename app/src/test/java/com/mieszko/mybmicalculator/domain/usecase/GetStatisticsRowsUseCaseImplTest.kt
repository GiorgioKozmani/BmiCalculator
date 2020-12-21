package com.mieszko.mybmicalculator.domain.usecase

import com.mieszko.mybmicalculator.di.module.RepositoryModule
import com.mieszko.mybmicalculator.domain.model.BmiStatistics
import com.mieszko.mybmicalculator.domain.repository.StatisticsRepository
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

class GetStatisticsRowsUseCaseImplTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(RepositoryModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `usecase calls repository and returns response`() {
        val testBmiStats = BmiStatistics()
        val mockRepoResponse = Single.just(testBmiStats)

        val mockRepository = declareMock<StatisticsRepository> {
            given(getStatistics()).willReturn(mockRepoResponse)
        }

        val useCaseResponse = GetStatisticsRowsUseCaseImpl(mockRepository).getStatistics()

        // repository gets called with right arguments
        Mockito.verify(mockRepository).getStatistics()
        // usecase returns repository response
        Assert.assertSame(mockRepoResponse.blockingGet(), useCaseResponse.blockingGet())
    }
}