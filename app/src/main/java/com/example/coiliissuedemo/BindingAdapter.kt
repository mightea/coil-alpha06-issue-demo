package com.example.coiliissuedemo

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.ViewSizeResolver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@BindingAdapter("imgUri", "fallbackImgUri", requireAll = false)
fun loadImage(view: ImageView, imgUri: Uri?, fallbackImgUri: Uri?) {
    CoroutineScope(Dispatchers.IO).launch {
        val uri = imgUri ?: Uri.EMPTY
        view.load(uri) {
            size(ViewSizeResolver(view))

            fallbackImgUri?.encodedPath?.substring(1)?.toInt()?.let { fallbackImgResId ->
                fallback(fallbackImgResId)
                error(fallbackImgResId)
                placeholder(fallbackImgResId)
            }
        }
    }
}
