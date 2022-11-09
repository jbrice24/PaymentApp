package com.payment.myapplication.ui.components.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.payment.myapplication.navigation.Screens
import com.payment.myapplication.presentation.PaymentViewModel
import com.payment.myapplication.presentation.model.DropDownItem
import com.payment.myapplication.ui.components.atoms.ButtonNext
import com.payment.myapplication.ui.components.template.CustomPage
import com.payment.myapplication.ui.components.organism.PaymentDropDownMenu
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun PaymentTypePage(
    navController: NavHostController,
    screenTitle: Int,
    amount: Int?,
    viewModel: PaymentViewModel
) {
    var selectedItem by remember { mutableStateOf(DropDownItem()) }
    var isError by remember { mutableStateOf(false) }

    CustomPage(screenTitle = screenTitle) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            PaymentDropDownMenu(
                items = viewModel.paymentTypeListState.map {
                    DropDownItem(title = it.paymentTypeName, image = it.paymentTypeImage)
                },
                isError = isError
            ) {
                isError = false
                selectedItem = it
            }

            ButtonNext(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                if (selectedItem.title.isNullOrEmpty()) {
                    isError = true
                } else {
                    viewModel.paymentSelected =
                        viewModel.paymentTypeListState.filter { it.paymentTypeName == selectedItem.title }
                            .first()

                    navController.navigate(
                        Screens.Bank.route.replace("/{amount}", "/${amount}")
                    )
                }
            }

        }
    }
}