package com.payment.myapplication.ui.components.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.payment.myapplication.navigation.Screens
import com.payment.myapplication.ui.components.atoms.ButtonNext
import com.payment.myapplication.ui.components.molecules.AmountInput
import com.payment.myapplication.ui.components.template.CustomPage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun AmountPage(navController: NavHostController, screenTitle: Int) {
    var amount by remember { mutableStateOf(0L) }
    var isError by remember { mutableStateOf(false) }

    CustomPage(screenTitle = screenTitle) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            AmountInput(
                modifier = Modifier.align(Alignment.Center),
                isError = isError,
            ) {
                amount = if (it.isNotEmpty()) {
                    it.toLong()
                } else {
                    0
                }
            }

            ButtonNext(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                if (amount > 0) {
                    isError = false
                    navController.navigate(Screens.PaymentType.route.replace("/{amount}","/${amount}"))
                } else {
                    isError = true
                }
            }
        }
    }
}