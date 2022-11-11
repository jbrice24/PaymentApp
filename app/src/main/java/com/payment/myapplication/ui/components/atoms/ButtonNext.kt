package com.payment.myapplication.ui.components.atoms

import android.widget.Toast
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.payment.myapplication.R
import com.payment.myapplication.core.utils.connectivityCheck.ConnectionState
import com.payment.myapplication.core.utils.connectivityCheck.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun ButtonNext(
    modifier: Modifier,
    buttonMessage: Int = R.string.text_next,
    onClick: () -> Unit
) {
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    val context = LocalContext.current

    Button(
        modifier = modifier,
        onClick = {
            if (isConnected) {
                onClick()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.text_alert_internet_connection),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    ) {
        Text(text = stringResource(id = buttonMessage))
    }
}