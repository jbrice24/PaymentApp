package com.payment.myapplication.domain.useCase

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.payment.myapplication.domain.repository.Repository
import com.payment.myapplication.presentation.model.Bank
import com.payment.myapplication.presentation.model.PaymentType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetBanksUseCaseTest {

    private val dataRepository = mock<Repository>()
    private val useCase = GetBanksUseCase(dataRepository)

    private suspend fun stubBanksResponse(paymentId: String) {
        whenever(
            dataRepository.fetchBanks(paymentId)
        ).thenReturn(flow { emit(listOf()) })
    }

    @Test
    fun `given getBanksUseCase objet, when call execute, then return list of Banks`() = runTest {
        val paymentId = "Random paymentid"
        stubBanksResponse(paymentId)
        useCase.execute(paymentId).test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(emptyList<Bank>())
            cancelAndIgnoreRemainingEvents()
        }
    }
}