package com.fi.practicaljanak.extensions

import android.content.Context
import android.widget.Toast
import java.math.BigDecimal
import java.util.regex.Matcher
import java.util.regex.Pattern

/**Show Toast message*/
fun Any.showToast(context: Context, message: String) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun Any.showLongToast(context: Context, message: String) =
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()

/**Get class name*/
fun Any.getClassName(): String {
    return this::class.java.simpleName
}

/**Null value check*/
fun String?.nullSafe(defaultValue: String = ""): String {
    return this ?: defaultValue
}

fun Int?.nullSafe(defaultValue: Int = 0): Int {
    return this ?: defaultValue
}

fun Float?.nullSafe(defaultValue: Float = 0.0f): Float {
    return this ?: defaultValue
}

fun Long?.nullSafe(defaultValue: Long = 0L): Long {
    return this ?: defaultValue
}

fun Double?.nullSafe(defaultValue: Double = 0.0): Double {
    return this ?: defaultValue
}

fun BigDecimal?.nullSafe(defaultValue: BigDecimal = BigDecimal(0)): BigDecimal {
    return this ?: defaultValue
}

fun Boolean?.nullSafe(defaultValue: Boolean = false): Boolean {
    return this ?: defaultValue
}

fun String.checkIsValidPanCard() : Boolean {
    val pattern: Pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.checkIsValidDay() : Boolean {
    return this.length == 2 && this.toInt().nullSafe(0) in 1..31
}
fun String.checkIsValidMonth() : Boolean {
    return this.length == 2 && this.toInt().nullSafe(0) in 1..12
}
fun String.checkIsValidYear() : Boolean {
    return this.length == 4 && this.toInt().nullSafe(0) in 1900..2022
}