package com.payment.myapplication.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payment.myapplication.domain.useCase.GetBanksUseCase
import com.payment.myapplication.domain.useCase.GetFeeUseCase
import com.payment.myapplication.domain.useCase.GetPaymentTypeUseCase
import com.payment.myapplication.presentation.model.Bank
import com.payment.myapplication.presentation.model.Fee
import com.payment.myapplication.presentation.model.FeeRequest
import com.payment.myapplication.presentation.model.PaymentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getPaymentTypeUseCase: GetPaymentTypeUseCase,
    private val getBanksUseCase: GetBanksUseCase,
    private val getFeesUseCase: GetFeeUseCase
    ) : ViewModel() {

    var paymentTypeListState by mutableStateOf(listOf<PaymentType>())
    var bankListState by mutableStateOf(listOf<Bank>())
    var feeListState by mutableStateOf(listOf<Fee>())

    fun fetchPaymentTypeList() {
        viewModelScope.launch {
            getPaymentTypeUseCase.execute().collect { paymentTypes ->
                paymentTypeListState = paymentTypes.sortedBy { it.paymentTypeName }
            }
        }
    }

    fun fetchBankList(paymentId: String) {
        viewModelScope.launch {
            getBanksUseCase.execute(paymentId).collect { banks ->
                bankListState = banks.sortedBy { it.name }
            }
        }
    }

    fun fetchFeeList(request: FeeRequest) {
        viewModelScope.launch {
            getFeesUseCase.execute(request).collect { fees ->
                feeListState = fees.sortedBy { it.message }
            }
        }
    }

}