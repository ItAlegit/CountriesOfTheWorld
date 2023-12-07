package com.example.countriesoftheworld

import android.widget.ImageView
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.Locale

fun languagesToString(map: Map<String, String>): String {
    return map.entries.joinToString { it.value }
}

fun formatNumber(number: Long) : String {
    val result = NumberFormat.getInstance().format(number)
    return result
}

suspend fun loadSvg(imageView: ImageView, url: String) {
    if (url.lowercase(Locale.ENGLISH).endsWith("svg")) {
        val imageLoader = ImageLoader.Builder(imageView.context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .target(imageView)
            .build()
        imageLoader.execute(request)
    } else {
        imageView.load(url)
    }
}