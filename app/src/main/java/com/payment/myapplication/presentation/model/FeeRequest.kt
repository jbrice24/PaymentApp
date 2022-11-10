package com.payment.myapplication.presentation.model

data class FeeRequest(
    val amount: String?,
    val paymentId: String?,
    val issuerId: String?
)
