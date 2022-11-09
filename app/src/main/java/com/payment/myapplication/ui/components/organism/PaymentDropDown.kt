package com.payment.myapplication.ui.components.organism

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.payment.myapplication.R
import com.payment.myapplication.presentation.model.DropDownItem

@ExperimentalMaterialApi
@Composable
fun PaymentDropDownMenu(
    items: List<DropDownItem>,
    isError: Boolean,
    onItemSelected: (DropDownItem) -> Unit
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(DropDownItem()) }
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedItem.title.toString(),
                onValueChange = { selectedItem = items.first { item -> item.title == it } },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = stringResource(R.string.text_select_payment_type)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                leadingIcon = if (!selectedItem.title.isNullOrEmpty()) {
                    {
                        AsyncImage(
                            modifier = Modifier.size(24.dp),
                            model = selectedItem.image,
                            imageLoader = imageLoader,
                            contentDescription = stringResource(id = R.string.text_image_payment_type).plus(
                                selectedItem.title
                            )
                        )
                    }
                } else null,
                readOnly = true,
                isError = isError
            )

            if (isError) {
                Text(
                    text = stringResource(R.string.text_error_empty_payment_type),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach {
                DropdownMenuItem(onClick = {
                    onItemSelected(it)
                    selectedItem = it
                    expanded = false
                }) {
                    AsyncImage(
                        imageLoader = imageLoader,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp),
                        model = it.image,
                        contentDescription = stringResource(id = R.string.text_image_payment_type).plus(
                            selectedItem.title
                        )
                    )
                    Text(text = it.title.toString())
                }
            }
        }
    }

}