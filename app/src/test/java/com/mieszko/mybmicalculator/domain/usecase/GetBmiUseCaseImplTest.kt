package com.mieszko.mybmicalculator.domain.usecase

import com.mieszko.mybmicalculator.di.module.RepositoryModule
import com.mieszko.mybmicalculator.domain.model.BmiInput
import com.mieszko.mybmicalculator.domain.repository.BmiRepository
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

class GetBmiUseCaseImplTest : KoinTest {

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
        val testInput = BmiInput(100, 50)
        val mockRepoResponse = Single.just("testText")

        val mockRepository = declareMock<BmiRepository> {
            given(getBmi(testInput)).willReturn(mockRepoResponse)
        }

        val useCaseResponse = GetBmiUseCaseImpl(mockRepository).getBmi(testInput)

        // repository gets called with right arguments
        Mockito.verify(mockRepository).getBmi(testInput)
        // usecase returns repository response
        Assert.assertSame(mockRepoResponse.blockingGet(), useCaseResponse.blockingGet())
    }
}