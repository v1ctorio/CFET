package com.nosesisaid.cfer.management.api

import android.content.Context
import com.github.kittinunf.fuel.httpDelete

fun deleteEmail(target_email_id:String,context: Context,callback: (String) -> Unit){
    val sharedPref = context.getSharedPreferences("cfer", Context.MODE_PRIVATE)
    val APIKey = sharedPref.getString("APIKey", "")
    val userId = sharedPref.getString("userId", "")
    if (APIKey == "" || userId == "") {
        callback("You must log in first")
        return
    }
    "https://api.cloudflare.com/client/v4/accounts/$userId/email/routing/addresses/$target_email_id"
        .httpDelete()
        .header("Authorization" to "Bearer $APIKey")
        .response { _, response, result ->
            when (result) {
                is com.github.kittinunf.result.Result.Failure -> {
                    val ex = result.getException()
                    callback("Error: ${ex.message}")
                }
                is com.github.kittinunf.result.Result.Success -> {
                    if (response.statusCode == 200) {
                        callback("success")
                    } else {
                        callback("Error: ${response.statusCode}")
                    }
                }
            }
        }
}