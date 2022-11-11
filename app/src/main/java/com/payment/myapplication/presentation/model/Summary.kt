package com.payment.myapplication.presentation.model

data class Summary(
    val amount: String = "",
    val paymentType: PaymentType = PaymentType(),
    val bank: Bank = Bank(),
    val fee: Fee = Fee()
)
