package com.mieszko.mybmicalculator.presentation

import com.mieszko.mybmicalculator.di.module.RepositoryModule
import com.mieszko.mybmicalculator.domain.model.BmiInput
import com.mieszko.mybmicalculator.domain.usecase.GetBmiUseCase
import com.mieszko.mybmicalculator.presentation.calculator.CalculatorViewModel
import com.mieszko.mybmicalculator.common.DisposablesBag
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito

class CalculatorViewModelTest : KoinTest {
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
        val useCaseResponse = Single.just(testResponseText)

        val mockUseCase = declareMock<GetBmiUseCase> {
            given(getBmi(testInput)).willReturn(useCaseResponse)
        }

        val mockDisposablesBag = declareMock<DisposablesBag> {}

        val viewModel = CalculatorViewModel(mockUseCase, mockDisposablesBag)

        viewModel.inputHeight = testInput.height
        viewModel.inputWeight = testInput.weight
        viewModel.performBmiCheck()

        // ongoing requests are cancelled
        Mockito.verify(mockDisposablesBag).clear()
        // new disposable is added to the bag
        Mockito.verify(mockDisposablesBag).add(any(Disposable::class.java))
        // usecase gets called with right arguments
        Mockito.verify(mockUseCase).getBmi(testInput)
        // usecase returns repository response
        Assert.assertSame(viewModel.bmiText.get(), testResponseText)
    }
}