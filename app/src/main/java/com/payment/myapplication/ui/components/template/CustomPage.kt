package com.payment.myapplication.ui.components.template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payment.myapplication.ui.components.molecules.Toolbar

@Composable
fun CustomPage(screenTitle: Int, content: @Composable () -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {
        Toolbar(screenTitle = screenTitle) {
            content()
        }
    }
}