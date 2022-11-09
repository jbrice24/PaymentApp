package com.payment.myapplication.ui.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.payment.myapplication.R

@ExperimentalComposeUiApi
@Composable
fun AmountInput(
    modifier: Modifier,
    maxChar: Int = 12,
    isError: Boolean,
    onAmountChange: (String) -> Unit
) {
    var amountValue by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = modifier,
            value = amountValue,
            onValueChange = {
                if (it.isDigitsOnly() && it.length < maxChar) {
                    amountValue = it
                    onAmountChange(it)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            isError = isError,
            maxLines = 1,
            label = { Text(text = stringResource(id = R.string.text_add_amount)) },
        )
        if (isError) {
            Text(
                text = stringResource(R.string.text_error_empty_amount),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

}