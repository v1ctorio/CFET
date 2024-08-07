package com.nosesisaid.cfer.management.api

import android.content.Context
import com.github.kittinunf.fuel.httpPut

fun updateCatchAllRule(targetEmail: String, enabled:Boolean, context: Context, callback: (String)->Unit){
    val sharedPref = context.getSharedPreferences("cfer", Context.MODE_PRIVATE)
    val APIKey = sharedPref.getString("APIKey", "")
    val userId = sharedPref.getString("userId", "")
    val zoneId = sharedPref.getString("zoneId", "")
    if (APIKey == "" || userId == "" || zoneId == "") {
        callback("You must log in first")
        return
    }

    val enabledString = if (enabled) "true" else "false"


    "https://api.cloudflare.com/client/v4/zones/$zoneId/email/routing/rules/catch_all"
        .httpPut()
        .header("Authorization" to "Bearer $APIKey")
        .header("Content-Type" to "application/json")
        .body("{\"actions\":[{\"type\":\"forward\",\"value\":[\"$targetEmail\"]}],\"enabled\":$enabledString,\"matchers\":[{\"type\":\"all\"}],\"name\":\"Send to $targetEmail rule.\"}")
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
