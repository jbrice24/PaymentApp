package com.payment.myapplication.ui.components.pages

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun PaymentTypePage(navController: NavHostController, screenTitle: Int, amount: Int?) {
    Text(text = "Welcome to Payment type, the amount is $amount")
}