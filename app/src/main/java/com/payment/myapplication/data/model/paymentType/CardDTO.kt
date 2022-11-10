package com.payment.myapplication.data.model.paymentType

data class CardDTO(
    val bin: Bin?,
    val card_number: CardNumber?,
    val security_code: SecurityCode?
)