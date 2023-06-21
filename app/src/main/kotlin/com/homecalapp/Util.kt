package com.homecalapp

import android.content.Context
import android.widget.Toast

const val PREFS_EMAIL: String = "email"
const val PREFS_PASSWORD: String = "password"
const val PREFS_PHONE: String = "phone"

inline fun Context.toast(text: String): Unit =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

inline fun Context.longToast(text: String): Unit =
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
