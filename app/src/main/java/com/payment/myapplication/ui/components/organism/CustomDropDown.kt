package com.payment.myapplication.ui.components.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.payment.myapplication.presentation.model.DropDownItem
import com.payment.myapplication.ui.components.molecules.LoadImageByUrl

@ExperimentalMaterialApi
@Composable
fun CustomDropDownMenu(
    items: List<DropDownItem>,
    isError: Boolean,
    label: Int,
    textError: Int,
    textSize: TextUnit = 16.sp,
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
                leadingIcon = if (!selectedItem.image.isNullOrEmpty()) {
                    {
                        selectedItem.image?.let { url ->
                            LoadImageByUrl(url)
                        }
                    }
                } else null,
                readOnly = true,
                isError = isError,
                textStyle = TextStyle(
                    fontSize = textSize
                )
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
                    if (!it.image.isNullOrEmpty()) {
                        LoadImageByUrl(it.image)
                    }

                    Text(
                        text = it.title.toString(),
                        style = TextStyle(
                            fontSize = textSize
                        )
                    )
                }
            }
        }
    }
}