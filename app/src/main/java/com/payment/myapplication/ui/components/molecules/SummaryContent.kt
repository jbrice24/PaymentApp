package com.payment.myapplication.ui.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.payment.myapplication.presentation.model.Summary

@Composable
fun SummaryContent(summary: Summary) {
    Column {
        Text(text = "Monto: ${summary.amount}")
        Text(text = "Medio de pago: ${summary.paymentType.paymentTypeName}")
        Text(text = "Banco: ${summary.bank.name}")
        Text(text = "Forma de pago: ${summary.fee.message.toString()}")
    }
}