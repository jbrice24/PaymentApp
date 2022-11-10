package com.payment.myapplication.ui.components.molecules

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.payment.myapplication.ui.components.atoms.GifImage
import com.payment.myapplication.ui.components.atoms.SvgImage

@Composable
fun LoadImageByUrl(url: String) {
    when {
        url.contains(".svg") -> {
            SvgImage(url)
        }
        url.contains(".gif") -> {
            GifImage(url)
        }
        else -> {
            AsyncImage(
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp),
                model = url,
                contentDescription = url
            )
        }
    }
}