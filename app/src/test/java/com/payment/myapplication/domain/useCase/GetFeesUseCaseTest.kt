package com.payment.myapplication.domain.useCase

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.payment.myapplication.data.model.fee.FeeRequestDTO
import com.payment.myapplication.domain.repository.Repository
import com.payment.myapplication.presentation.model.Bank
import com.payment.myapplication.presentation.model.Fee
import com.payment.myapplication.presentation.model.FeeRequest
import com.payment.myapplication.presentation.model.PaymentType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetFeesUseCaseTest {

    private val dataRepository = mock<Repository>()
    private val useCase = GetFeeUseCase(dataRepository)

    private suspend fun stubFeesResponse(request: FeeRequestDTO) {
        whenever(
            dataRepository.fetchFee(request)
        ).thenReturn(flow { emit(listOf()) })
    }

    @Test
    fun `given getBanksUseCase objet, when call execute, then return list of Banks`() = runTest {
        val paymentId = "Random paymentid"
        val issuerId = "Random issuerid"
        val amount = "Random amount"
        stubFeesResponse(FeeRequestDTO(amount, paymentId, issuerId))
        useCase.execute(FeeRequest(amount, paymentId, issuerId)).test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(emptyList<Fee>())
            cancelAndIgnoreRemainingEvents()
        }
    }
}