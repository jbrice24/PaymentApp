package com.payment.myapplication.domain.useCase

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.payment.myapplication.data.DataRepository
import com.payment.myapplication.data.model.fee.FeeItemDTO
import com.payment.myapplication.data.model.fee.FeeRequestDTO
import com.payment.myapplication.domain.repository.Repository
import com.payment.myapplication.presentation.model.PaymentType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetPaymentTypeUseCaseTest {

    private val dataRepository = mock<Repository>()
    private val useCase = GetPaymentTypeUseCase(dataRepository)

    private suspend fun stubPaymentTypeResponse() {
        whenever(
            dataRepository.fetchPaymentType()
        ).thenReturn(flow { emit(listOf()) })
    }

    @Test
    fun `given getPaymentTypeUseCase objet, when call execute, then return list of PaymentType`() = runTest {
        stubPaymentTypeResponse()
        useCase.execute().test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(emptyList<PaymentType>())
            cancelAndIgnoreRemainingEvents()
        }
    }
}