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
import com.payment.myapplication.ui.components.pages.*
import com.payment.myapplication.utils.constants.ScreensConstants.AMOUNT
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
            route = Screens.Amount.route) {
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
            )
        ) {
            viewModel.fetchBankList()
            BankPage(
                navController = navController,
                Screens.Bank.resourceId,
                it.arguments?.getInt(AMOUNT),
                viewModel
            )
        }
        composable(
            route = Screens.Fee.route,
            arguments = listOf(
                navArgument(AMOUNT) { type = NavType.IntType },
            )
        ) {
            FeePage(
                navController = navController,
                Screens.Fee.resourceId,
                it.arguments?.getInt(AMOUNT),
            )
        }
    }
}