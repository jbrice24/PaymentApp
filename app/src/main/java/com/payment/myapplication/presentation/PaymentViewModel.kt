package com.payment.myapplication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payment.myapplication.domain.useCase.GetBanksUseCase
import com.payment.myapplication.domain.useCase.GetFeeUseCase
import com.payment.myapplication.domain.useCase.GetPaymentTypeUseCase
import com.payment.myapplication.presentation.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getPaymentTypeUseCase: GetPaymentTypeUseCase,
    private val getBanksUseCase: GetBanksUseCase,
    private val getFeesUseCase: GetFeeUseCase
) : ViewModel() {

    var errorState by mutableStateOf(false)
    var paymentTypeListState by mutableStateOf(listOf<PaymentType>())
    var bankListState by mutableStateOf(listOf<Bank>())
    var feeListState by mutableStateOf(listOf<Fee>())
    var amount by mutableStateOf(0L)
    var paymentTypeSelected by mutableStateOf(PaymentType())
    var bankSelected by mutableStateOf(Bank())
    var feeSelected by mutableStateOf(Fee())
    var summary by mutableStateOf(
        Summary(
            amount = amount.toString(),
            paymentType = paymentTypeSelected,
            bank = bankSelected,
            fee = feeSelected
        )
    )

    fun fetchPaymentTypeList() {
        viewModelScope.launch {
            getPaymentTypeUseCase.execute()
                .handleErrors { errorState = true }
                .collect { paymentTypes ->
                    errorState = false
                    paymentTypeListState = paymentTypes.sortedBy { it.paymentTypeName }
                }
        }
    }

    fun fetchBankList(paymentId: String) {
        viewModelScope.launch {
            getBanksUseCase.execute(paymentId)
                .handleErrors { errorState = true }
                .collect { banks ->
                    errorState = false
                    bankListState = banks.sortedBy { it.name }
            }
        }
    }

    fun fetchFeeList(request: FeeRequest) {
        viewModelScope.launch {
            getFeesUseCase.execute(request)
                .handleErrors { errorState = true }
                .collect { fees ->
                    errorState = false
                    feeListState = fees.sortedBy { it.message }
            }
        }
    }

}

fun <T> Flow<T>.handleErrors(onError: () -> Unit): Flow<T> = flow {
    try {
        collect { value -> emit(value) }
    } catch (e: Throwable) {
        onError()
    }
}