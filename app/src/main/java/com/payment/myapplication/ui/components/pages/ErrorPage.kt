package com.payment.myapplication.ui.components.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.payment.myapplication.R
import com.payment.myapplication.ui.components.atoms.ButtonNext
import com.payment.myapplication.ui.components.molecules.CustomAnimation
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun ErrorPage(onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            CustomAnimation(animation = R.raw.error)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = stringResource(R.string.text_error_message),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            ButtonNext(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                buttonMessage = R.string.text_retry
            ) {
                onRetry()
            }
        }

    }
}