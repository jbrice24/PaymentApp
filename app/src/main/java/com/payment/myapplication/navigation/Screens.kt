package com.payment.myapplication.navigation

import androidx.annotation.StringRes
import com.payment.myapplication.R
import com.payment.myapplication.utils.constants.ScreensConstants.AMOUNT_SCREEN
import com.payment.myapplication.utils.constants.ScreensConstants.BANK_SCREEN
import com.payment.myapplication.utils.constants.ScreensConstants.FEE_SCREEN
import com.payment.myapplication.utils.constants.ScreensConstants.PAYMENT_TYPE_SCREEN
import com.payment.myapplication.utils.constants.ScreensConstants.SPLASH_SCREEN

sealed class Screens(val route: String, @StringRes val resourceId: Int = R.string.app_name) {
    object Splash : Screens(SPLASH_SCREEN)
    object Amount : Screens(AMOUNT_SCREEN, R.string.title_amount_screen)
    object PaymentType : Screens(PAYMENT_TYPE_SCREEN, R.string.title_payment_type_screen)
    object Bank : Screens(BANK_SCREEN, R.string.title_bank_screen)
    object Fee : Screens(FEE_SCREEN, R.string.title_fee_screen)
}
