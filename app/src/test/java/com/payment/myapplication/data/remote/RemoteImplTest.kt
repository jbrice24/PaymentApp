package com.payment.myapplication.data.remote

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.payment.myapplication.BuildConfig
import com.payment.myapplication.data.model.banks.BankItemDTO
import com.payment.myapplication.data.model.fee.FeeItemDTO
import com.payment.myapplication.data.model.fee.FeeRequestDTO
import com.payment.myapplication.data.model.paymentType.ItemDTO
import com.payment.myapplication.data.retrofit.ApiService
import com.payment.myapplication.data.source.Remote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RemoteImplTest {

    private lateinit var remoteImpl: Remote
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = mock()
        remoteImpl = RemoteImpl(apiService)
    }

    private suspend fun stubPaymentTypeResponse() {
        whenever(
            apiService.fetchPaymentType(BuildConfig.API_KEY_MELI)
        ).thenReturn(listOf())
    }

    private suspend fun stubBankResponse(paymentId: String) {
        whenever(
            apiService.fetchBanks(BuildConfig.API_KEY_MELI, paymentId)
        ).thenReturn(listOf())
    }

    private suspend fun stubFeeResponse(paymentId: String, issuerId: String, amount: String) {
        whenever(
            apiService.fetchFees(BuildConfig.API_KEY_MELI, paymentId, issuerId, amount)
        ).thenReturn(listOf())
    }

    @Test
    fun `given remote objet, when call fetchPaymentType, then return list of ItemsDTO`() = runTest {
        stubPaymentTypeResponse()
        remoteImpl.fetchPaymentType().test {
            val emission = awaitItem()
            assertThat(emission).isEqualTo(emptyList<ItemDTO>())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `given remote objet, when call fetchBanks, then return list of BankDTO`() = runTest {
        val paymentId = "Random paymentid"
        stubBankResponse(paymentId)
        remoteImpl.fetchBanks(paymentId).test {
            val emission = awaitItem()
            assertThat(emission).isEqualTo(emptyList<BankItemDTO>())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `given remote objet, when call fetchFee, then return list of FeeDTO`() = runTest {
        val paymentId = "Random paymentid"
        val issuerId = "Random issuerid"
        val amount = "Random amount"
        stubFeeResponse(paymentId, issuerId, amount)
        remoteImpl.fetchFee(FeeRequestDTO(amount, paymentId, issuerId)).test {
            val emission = awaitItem()
            assertThat(emission).isEqualTo(emptyList<FeeItemDTO>())
            cancelAndIgnoreRemainingEvents()
        }
    }

}