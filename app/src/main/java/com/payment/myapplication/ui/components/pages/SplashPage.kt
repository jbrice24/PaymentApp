package com.payment.myapplication.ui.components.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.payment.myapplication.R
import com.payment.myapplication.navigation.Screens
import com.payment.myapplication.ui.components.molecules.CustomAnimation

@Composable
fun SplashPage(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        CustomAnimation(animation = R.raw.splash) {
            navController.navigate(Screens.Amount.route) {
                popUpTo(Screens.Splash.route) { inclusive = true }
            }
        }
    }
}