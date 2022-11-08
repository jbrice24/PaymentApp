package com.payment.myapplication.ui.components.pages

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.payment.myapplication.ui.components.organism.CustomPage

@Composable
fun AmountPage(navController: NavHostController, screenTitle: Int) {
    CustomPage(screenTitle = screenTitle) {
        Text(text = "Welcome to amount")
    }
}