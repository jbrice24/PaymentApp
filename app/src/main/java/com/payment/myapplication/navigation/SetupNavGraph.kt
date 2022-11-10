package com.payment.myapplication.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.payment.myapplication.presentation.PaymentViewModel
import com.payment.myapplication.presentation.model.FeeRequest
import com.payment.myapplication.ui.components.pages.*
import com.payment.myapplication.utils.constants.ScreensConstants.AMOUNT
import com.payment.myapplication.utils.constants.ScreensConstants.ISSUER_ID
import com.payment.myapplication.utils.constants.ScreensConstants.PAYMENT_ID
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: PaymentViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashPage(navController = navController)
        }
        composable(
            route = Screens.Amount.route
        ) {
            AmountPage(navController = navController, Screens.Amount.resourceId)
        }
        composable(
            route = Screens.PaymentType.route,
            arguments = listOf(navArgument(AMOUNT) { type = NavType.IntType })
        ) {
            viewModel.fetchPaymentTypeList()
            PaymentTypePage(
                navController = navController,
                Screens.PaymentType.resourceId,
                it.arguments?.getInt(AMOUNT),
                viewModel
            )
        }
        composable(
            route = Screens.Bank.route,
            arguments = listOf(
                navArgument(AMOUNT) { type = NavType.IntType },
                navArgument(PAYMENT_ID) { type = NavType.StringType },
            )
        ) {
            it.arguments?.getString(PAYMENT_ID)?.let { paymentId ->
                viewModel.fetchBankList(paymentId)
            }
            BankPage(
                navController = navController,
                Screens.Bank.resourceId,
                it.arguments?.getInt(AMOUNT),
                it.arguments?.getString(PAYMENT_ID),
                viewModel
            )
        }
        composable(
            route = Screens.Fee.route,
            arguments = listOf(
                navArgument(AMOUNT) { type = NavType.IntType },
                navArgument(PAYMENT_ID) { type = NavType.StringType },
                navArgument(ISSUER_ID) { type = NavType.StringType },
            )
        ) {
            val request = FeeRequest(
                amount = it.arguments?.getInt(AMOUNT).toString(),
                paymentId = it.arguments?.getString(PAYMENT_ID),
                issuerId = it.arguments?.getString(ISSUER_ID)
            )

            viewModel.fetchFeeList(request)
            FeePage(
                navController = navController,
                Screens.Fee.resourceId,
                it.arguments?.getInt(AMOUNT),
                it.arguments?.getString(PAYMENT_ID),
                it.arguments?.getString(ISSUER_ID),
                viewModel
            )
        }
    }
}