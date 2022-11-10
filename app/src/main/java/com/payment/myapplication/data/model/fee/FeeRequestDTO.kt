package com.payment.myapplication.data.model.fee

data class FeeRequestDTO(
    val amount: String?,
    val paymentId: String?,
    val issuerId: String?
)
