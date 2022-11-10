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
import androidx.navigation.NavHostController
import com.payment.myapplication.navigation.Screens
import com.payment.myapplication.presentation.PaymentViewModel
import com.payment.myapplication.presentation.model.DropDownItem
import com.payment.myapplication.ui.components.atoms.ButtonNext
import com.payment.myapplication.ui.components.organism.CustomDropDownMenu
import com.payment.myapplication.ui.components.template.CustomPage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.payment.myapplication.R

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun BankPage(
    navController: NavHostController,
    screenTitle: Int,
    amount: Int?,
    paymentId: String?,
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
                items = viewModel.bankListState.map {
                    DropDownItem(title = it.name, image = it.image)
                },
                label = R.string.text_select_bank,
                textError = R.string.text_error_empty_bank,
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
                    val bankSelected =
                        viewModel.bankListState.first { it.name == selectedItem.title }

                    navController.navigate(
                        Screens.Fee.route
                            .replace("/{amount}", "/${amount}")
                            .replace("/{paymentId}", "/${paymentId}")
                            .replace("/{issuerId}", "/${bankSelected.issuerId}")
                    )
                }
            }

        }
    }
}