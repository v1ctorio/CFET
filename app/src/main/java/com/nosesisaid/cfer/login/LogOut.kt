package com.nosesisaid.cfer.login

import android.content.Context

fun logOut(context: Context, callback: (String) -> Unit) {
    val sharedPref = context.getSharedPreferences("cfer", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        remove("zoneId")
        remove("APIKey")
        remove("domain")
        remove("userId")
        apply()
    }
    callback("success")
}