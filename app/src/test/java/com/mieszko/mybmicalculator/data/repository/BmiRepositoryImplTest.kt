package com.mieszko.mybmicalculator.data.repository

import com.mieszko.mybmicalculator.data.source.remote.BmiApi
import com.mieszko.mybmicalculator.di.module.NetworkModule
import com.mieszko.mybmicalculator.domain.model.BmiInput
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

class BmiRepositoryImplTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(NetworkModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `getBmi calls server and returns response`() {
        val testInput = BmiInput(100, 50)
        val mockServerResponse = Single.just("testText")

        val testApi = declareMock<BmiApi> {
            given(getBmi(testInput.height, testInput.weight)).willReturn(mockServerResponse)
        }

        val repositoryResponse = BmiRepositoryImpl(testApi).getBmi(testInput)

        // api gets called with right arguments
        Mockito.verify(testApi).getBmi(testInput.height, testInput.weight)
        // repository returns server response
        Assert.assertSame(mockServerResponse, repositoryResponse)
    }
}