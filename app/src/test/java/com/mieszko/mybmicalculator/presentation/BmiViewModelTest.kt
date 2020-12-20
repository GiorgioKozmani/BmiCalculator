package com.mieszko.mybmicalculator.presentation

import com.mieszko.mybmicalculator.di.module.RepositoryModule
import com.mieszko.mybmicalculator.domain.model.BmiInput
import com.mieszko.mybmicalculator.domain.usecase.GetBmiUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito

class BmiViewModelTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(RepositoryModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `on performBmiCheck viewmodel calls usecase and updates bmiText with response`() {
        val testInput = BmiInput(100, 50)
        val testResponseText = "testText"

        val mockUseCase = declareMock<GetBmiUseCase> {
            given(getBmi(testInput)).willReturn(Single.just(testResponseText))
        }

        val viewModel = BmiViewModel(mockUseCase, CompositeDisposable())

        viewModel.inputHeight = testInput.height
        viewModel.inputWeight = testInput.weight
        viewModel.performBmiCheck()

        // usecase gets called with right arguments
        Mockito.verify(mockUseCase).getBmi(testInput)
        // usecase returns repository response
        Assert.assertSame(viewModel.bmiText.get(), testResponseText)
    }
}