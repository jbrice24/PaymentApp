package com.payment.myapplication.ui.components.pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.payment.myapplication.R
import com.payment.myapplication.core.navigation.Screens
import com.payment.myapplication.presentation.PaymentViewModel
import com.payment.myapplication.presentation.model.DropDownItem
import com.payment.myapplication.ui.components.atoms.ButtonNext
import com.payment.myapplication.ui.components.template.CustomPage
import com.payment.myapplication.ui.components.organism.CustomDropDownMenu
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
        if(viewModel.errorState) {
            ErrorPage {
                viewModel.fetchPaymentTypeList()
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                CustomDropDownMenu(
                    items = viewModel.paymentTypeListState.map {
                        DropDownItem(title = it.paymentTypeName, image = it.paymentTypeImage)
                    },
                    label = R.string.text_select_payment_type,
                    textError = R.string.text_error_empty_payment_type,
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
                        val paymentSelected =
                            viewModel.paymentTypeListState.first { it.paymentTypeName == selectedItem.title }

                        viewModel.paymentTypeSelected = paymentSelected

                        navController.navigate(
                            Screens.Bank.route
                                .replace("/{amount}", "/${amount}")
                                .replace("/{paymentId}", "/${paymentSelected.paymentId}")
                        )
                    }
                }

            }
        }
    }
}