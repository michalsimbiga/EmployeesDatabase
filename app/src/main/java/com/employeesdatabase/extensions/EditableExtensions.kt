package com.employeesdatabase.extensions

import android.text.Editable

fun Editable.onNotBlankCallback(callback: () -> Unit) {
    if (isNotBlank()) callback()
}