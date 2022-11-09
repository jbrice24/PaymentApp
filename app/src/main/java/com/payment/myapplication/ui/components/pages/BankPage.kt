package com.payment.myapplication.ui.components.pages

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.payment.myapplication.domain.model.PaymentType
import com.payment.myapplication.presentation.PaymentViewModel
import com.payment.myapplication.ui.components.template.CustomPage

@Composable
fun BankPage(
    navController: NavHostController,
    screenTitle: Int,
    amount: Int?,
    viewModel: PaymentViewModel
) {
    CustomPage(screenTitle = screenTitle) {
        Text(text = "Welcom to bank page, payment selected: ${viewModel.paymentSelected.paymentId}")
    }
}