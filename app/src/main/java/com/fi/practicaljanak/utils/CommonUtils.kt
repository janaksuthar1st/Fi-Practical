package com.fi.practicaljanak.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.fi.practicaljanak.R

object CommonUtils {


    fun Context.checkInternetConnected(): Boolean {
        var isConnected = false
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    isConnected = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                @Suppress("DEPRECATION") cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        isConnected = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        isConnected = true
                    }
                }
            }
        }
        return isConnected
    }

    fun getBankSetupDescSpannableStringBuilder(
        context: Context,
        prefix: String,
        postFix: String,
        operation: (Int) -> Unit
    ): SpannableStringBuilder {
        val builder = SpannableStringBuilder()

        val txtPrefix =
            SpannableString(prefix)
        builder.append(txtPrefix)

        val txtPostFix =
            SpannableString(postFix)
        txtPostFix.setSpan(
            MyClickableSpan(LEARN_MORE, operation),
            0,
            txtPostFix.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtPostFix.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.textColorPurple
                )
            ), 0, txtPostFix.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        builder.append(txtPostFix)

        return builder
    }

    class MyClickableSpan(val position: Int, val operation: (Int) -> Unit) : ClickableSpan() {
        override fun onClick(p0: View) {
            when (position) {
                LEARN_MORE -> {
                    operation.invoke(LEARN_MORE)
                }
                else -> {
                    operation.invoke(LEARN_MORE)
                }
            }
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = true
        }
    }

    fun openBrowser(context: Context, url: String) {
        val viewIntent = Intent("android.intent.action.VIEW", Uri.parse(url))
        context.startActivity(viewIntent)
    }


}
