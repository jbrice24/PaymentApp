package com.payment.myapplication.ui.components.pages

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.payment.myapplication.ui.components.template.CustomPage

@Composable
fun FeePage(
    navController: NavHostController,
    screenTitle: Int,
    amount: Int?,
) {
    CustomPage(screenTitle = screenTitle) {

    }
}