package com.payment.myapplication.ui.components.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.payment.myapplication.R
import com.payment.myapplication.navigation.Screens
import com.payment.myapplication.presentation.PaymentViewModel
import com.payment.myapplication.presentation.model.DropDownItem
import com.payment.myapplication.ui.components.atoms.ButtonNext
import com.payment.myapplication.ui.components.organism.CustomDropDownMenu
import com.payment.myapplication.ui.components.template.CustomPage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun FeePage(
    navController: NavHostController,
    screenTitle: Int,
    amount: Int?,
    paymentId: String?,
    issuerID: String?,
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
            CustomDropDownMenu(
                items = viewModel.feeListState.map {
                    DropDownItem(title = it.message)
                },
                label = R.string.text_select_fee,
                textError = R.string.text_error_empty_fee,
                isError = isError,
                textSize = 14.sp
            ) {
                isError = false
                selectedItem = it
            }

            ButtonNext(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                buttonMessage = R.string.text_finish_payment
            ) {
                if (selectedItem.title.isNullOrEmpty()) {
                    isError = true
                } else {
                    navController.navigate(Screens.Amount.route) {
                        popUpTo(Screens.PaymentType.route) { inclusive = true }
                    }
                }
            }

        }
    }
}