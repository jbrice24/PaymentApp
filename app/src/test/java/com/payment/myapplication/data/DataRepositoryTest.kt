package com.payment.myapplication.data

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.payment.myapplication.data.model.banks.BankItemDTO
import com.payment.myapplication.data.model.fee.FeeItemDTO
import com.payment.myapplication.data.model.fee.FeeRequestDTO
import com.payment.myapplication.data.model.paymentType.ItemDTO
import com.payment.myapplication.data.remote.RemoteImpl
import com.payment.myapplication.data.sourceFactory.DataSourceFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DataRepositoryTest {

    private val remote = mock<RemoteImpl>()
    private val sourceFactory = mock<DataSourceFactory>()
    private val dataRepository = DataRepository(sourceFactory)

    @Before
    fun setUp() {
        stubFactoryGetRemoteDataSource()
    }

    private fun stubFactoryGetRemoteDataSource() {
        whenever(sourceFactory.getRemote()).thenReturn(remote)
    }

    private suspend fun stubPaymentTypeResponse() {
        whenever(
            sourceFactory.getRemote().fetchPaymentType()
        ).thenReturn(flow { emit(listOf()) })
    }

    private suspend fun stubBankResponse(paymentId: String) {
        whenever(
            sourceFactory.getRemote().fetchBanks(paymentId)
        ).thenReturn(flow { emit(listOf()) })
    }

    private suspend fun stubFeeResponse(paymentId: String, issuerId: String, amount: String) {
        whenever(
            sourceFactory.getRemote().fetchFee(FeeRequestDTO(amount, paymentId, issuerId))
        ).thenReturn(flow { emit(listOf()) })
    }

    @Test
    fun `given dataRepository objet, when call fetchPaymentType, then return list of ItemsDTO`() = runTest {
        stubPaymentTypeResponse()
        dataRepository.fetchPaymentType().test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(emptyList<ItemDTO>())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `given dataRepository objet, when call fetchBanks, then return list of BankDTO`() = runTest {
        val paymentId = "Random paymentid"
        stubBankResponse(paymentId)
        dataRepository.fetchBanks(paymentId).test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(emptyList<BankItemDTO>())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `given dataRepository objet, when call fetchFee, then return list of FeeDTO`() = runTest {
        val paymentId = "Random paymentid"
        val issuerId = "Random issuerid"
        val amount = "Random amount"
        stubFeeResponse(paymentId, issuerId, amount)
        dataRepository.fetchFee(FeeRequestDTO(amount, paymentId, issuerId)).test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(emptyList<FeeItemDTO>())
            cancelAndIgnoreRemainingEvents()
        }
    }

}