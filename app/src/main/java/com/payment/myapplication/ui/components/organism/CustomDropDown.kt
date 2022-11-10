package com.payment.myapplication.ui.components.organism

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
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
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.payment.myapplication.R
import com.payment.myapplication.presentation.model.DropDownItem
import com.payment.myapplication.ui.components.molecules.LoadImageByUrl

@ExperimentalMaterialApi
@Composable
fun CustomDropDownMenu(
    items: List<DropDownItem>,
    isError: Boolean,
    label: Int,
    textError: Int,
    onItemSelected: (DropDownItem) -> Unit
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(DropDownItem()) }

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
                label = { Text(text = stringResource(label)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                leadingIcon = if (!selectedItem.title.isNullOrEmpty()) {
                    {
                        selectedItem.image?.let { url ->
                            LoadImageByUrl(url)
                        }
                    }
                } else null,
                readOnly = true,
                isError = isError
            )

            if (isError) {
                Text(
                    text = stringResource(textError),
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
                    it.image?.let { url ->
                        LoadImageByUrl(url)
                    }

                    Text(text = it.title.toString())
                }
            }
        }
    }
}