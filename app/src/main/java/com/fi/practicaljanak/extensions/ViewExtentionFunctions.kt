package com.fi.practicaljanak.extensions

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.fi.practicaljanak.R
import com.fi.practicaljanak.utils.GlideApp
import com.fi.practicaljanak.utils.GlideUrlWithCache
import java.io.File

fun View.showView() {
    visibility = View.VISIBLE
}

fun View.hideView() {
    visibility = View.GONE
}

fun View.invisibleView() {
    visibility = View.INVISIBLE
}

fun AppCompatImageView.setAlbumArt(
    context: Context,
    url: String?,
    radius: Int = 0,
    placeHolder: Int? = R.drawable.icn_fi_logo
) {
    try {
        if (radius != 0) {
            GlideApp.with(context).load(
                if (url.nullSafe()
                        .contains("http")
                ) GlideUrlWithCache(url.nullSafe()) else File(url.nullSafe())
            ).transform(CenterCrop(), RoundedCorners(radius))
                .placeholder(placeHolder!!).error(placeHolder).into(this)
        } else {
            GlideApp.with(context).load(
                if (url.nullSafe()
                        .contains("http")
                ) GlideUrlWithCache(url.nullSafe()) else File(url.nullSafe())
            ).transform(CenterCrop())
                .placeholder(placeHolder!!).error(placeHolder).into(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun AppCompatButton.enable(){
    this.isEnabled = true
    this.alpha = 1f
}
fun AppCompatButton.disable(){
    this.isEnabled = false
    this.alpha = 0.5f
}