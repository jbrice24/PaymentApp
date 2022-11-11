package com.payment.myapplication.presentation

import com.payment.myapplication.domain.useCase.GetBanksUseCase
import com.payment.myapplication.domain.useCase.GetFeeUseCase
import com.payment.myapplication.domain.useCase.GetPaymentTypeUseCase
import com.payment.myapplication.presentation.model.FeeRequest
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
class PaymentViewModelTest {
    private val getPaymentTypeUseCase = mock<GetPaymentTypeUseCase>()
    private val getBanksUseCase = mock<GetBanksUseCase>()
    private val getFeesUseCase = mock<GetFeeUseCase>()
    private val viewModel = PaymentViewModel(getPaymentTypeUseCase, getBanksUseCase, getFeesUseCase)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    private suspend fun stubPaymentTypeUseCaseResponse() {
        whenever(
            getPaymentTypeUseCase.execute()
        ).thenReturn(flow { emit(listOf()) })
    }

    private suspend fun stubBanksUseCaseResponse(paymentId: String) {
        whenever(
            getBanksUseCase.execute(paymentId)
        ).thenReturn(flow { emit(listOf()) })
    }

    private suspend fun stubFeesUseCaseResponse(request: FeeRequest) {
        whenever(
            getFeesUseCase.execute(request)
        ).thenReturn(flow { emit(listOf()) })
    }

    @Test
    fun `given viewModel objet, when call fetchPaymentTypeList, then return list of PaymentType`() = runTest {
        stubPaymentTypeUseCaseResponse()
        viewModel.fetchPaymentTypeList()
        assertTrue(viewModel.paymentTypeListState.isEmpty())
    }

    @Test
    fun `given viewModel objet, when call fetchBankList, then return list of PaymentType`() = runTest {
        val paymentId = "Random paymentid"
        stubBanksUseCaseResponse(paymentId)
        viewModel.fetchBankList(paymentId)
        assertTrue(viewModel.paymentTypeListState.isEmpty())
    }

    @Test
    fun `given viewModel objet, when call fetchFeeList, then return list of PaymentType`() = runTest {
        val paymentId = "Random paymentid"
        val issuerId = "Random issuerid"
        val amount = "Random amount"
        stubPaymentTypeUseCaseResponse()
        viewModel.fetchFeeList(FeeRequest(amount, paymentId, issuerId))
        assertTrue(viewModel.paymentTypeListState.isEmpty())
    }


}