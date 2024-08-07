package com.nosesisaid.cfer.management.api

import android.content.Context
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.google.gson.JsonObject

data class listRoutesResponse(
    val result: List<route>,
    val success: Boolean,
    val result_info: resultInfo,
    val errors: List<CloudflareError>,
    val messages: List<CloudflareMessage>
)

data class route(
    val actions: List<action>,
    val enabled: Boolean,
    val id: String,
    val matchers: List<matcher>,
    val name: String,
    val priority: Int,
    val tag: String
)
data class action (
    val type: String,
    val value: List<String>
)

data class matcher(
    val field: String,
    val type: String,
    val value: String
)

fun fetchRoutes(page: Int, context: Context, callback: (listRoutesResponse?) -> Unit) {
    val sharedPref = context.getSharedPreferences("cfer", Context.MODE_PRIVATE)
    val account_identifier = sharedPref.getString("userId", "null") ?: "null"
    val token = sharedPref.getString("APIKey", "null") ?: "null"
    val zoneId = sharedPref.getString("zoneId", "null") ?: "null"


    println("$zoneId,$token,$account_identifier")
    if (account_identifier == "null") {
        callback(null)
        return
    }

    var a =
        "https://api.cloudflare.com/client/v4/zones/$zoneId/email/routing/rules"
            .httpGet()
            .header("Authorization" to "Bearer $token")
            .header("Content-Type" to "application/json")
            .header("Accept" to "application/json")
            .responseString { request, response, result ->
                println(request)
                println(response)
                println(result)

                when (result) {
                    is com.github.kittinunf.result.Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                        callback(null)
                    }

                    is com.github.kittinunf.result.Result.Success -> {
                        val data = result.get()
                        val jsonObject = Gson().fromJson(data, JsonObject::class.java)
                        val routeListResponse =
                            Gson().fromJson(jsonObject, listRoutesResponse::class.java)
                        println(routeListResponse)
                        callback(routeListResponse)
                    }
                }
            }


}