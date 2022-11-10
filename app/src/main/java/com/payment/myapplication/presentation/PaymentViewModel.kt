package com.payment.myapplication.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payment.myapplication.domain.model.Bank
import com.payment.myapplication.domain.model.PaymentType
import com.payment.myapplication.domain.useCase.GetBanksUseCase
import com.payment.myapplication.domain.useCase.GetPaymentTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getPaymentTypeUseCase: GetPaymentTypeUseCase,
    private val getBanksUseCase: GetBanksUseCase,
    ) : ViewModel() {

    var paymentTypeListState by mutableStateOf(listOf<PaymentType>())
    var bankListState by mutableStateOf(listOf<Bank>())
    var paymentSelected: PaymentType = PaymentType()
    var bankSelected: Bank = Bank()

    fun fetchPaymentTypeList() {
        viewModelScope.launch {
            getPaymentTypeUseCase.execute().collect { paymentTypes ->
                paymentTypeListState = paymentTypes.sortedBy { it.paymentTypeName }
            }
        }
    }

    fun fetchBankList() {
        viewModelScope.launch {
            paymentSelected.paymentId?.let { paymentMethodId ->
                getBanksUseCase.execute(paymentMethodId).collect { banks ->
                    bankListState = banks.sortedBy { it.name }
                }
            }

        }
    }

}