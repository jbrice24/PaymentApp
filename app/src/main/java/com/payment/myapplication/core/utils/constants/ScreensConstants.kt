package com.payment.myapplication.core.utils.constants

object ScreensConstants {
    const val SPLASH_SCREEN = "splash_screen"
    const val AMOUNT_SCREEN = "amount_screen"
    const val PAYMENT_TYPE_SCREEN = "payment_type_screen/{amount}"
    const val BANK_SCREEN = "bank_screen/{amount}/{paymentId}"
    const val FEE_SCREEN = "fee_screen/{amount}/{paymentId}/{issuerId}"
    const val AMOUNT = "amount"
    const val PAYMENT_ID = "paymentId"
    const val ISSUER_ID = "issuerId"
}