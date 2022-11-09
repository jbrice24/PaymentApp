package com.payment.myapplication.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payment.myapplication.domain.model.PaymentType
import com.payment.myapplication.domain.useCase.GetPaymentTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentTypeUseCase: GetPaymentTypeUseCase
    ) : ViewModel() {

    var paymentTypeListState by mutableStateOf(listOf<PaymentType>())
    var paymentSelected: PaymentType = PaymentType()

    fun fetchPaymentTypeList() {
        viewModelScope.launch {
            paymentTypeUseCase.execute().collect {
                paymentTypeListState = it
            }
        }
    }

}