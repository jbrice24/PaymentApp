package com.payment.myapplication.ui.components.molecules

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun Toolbar(screenTitle: Int, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(screenTitle)) }
            )
        }
    ) {
        content()
    }
}