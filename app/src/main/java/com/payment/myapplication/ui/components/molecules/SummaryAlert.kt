package com.payment.myapplication.ui.components.molecules

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SummaryAlert(
    title: String,
    confirmButton: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = title)
        },
        text = content,
        confirmButton = {
            Button(
                onClick = { onConfirm() }
            ) {
                Text(confirmButton)
            }
        }
    )
}