package com.nosesisaid.cfer.management

import android.content.Context
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson

//Types
data class resultInfo (
    val count: Int,
    val page: Int,
    val per_page: Int,
    val total_count: Int,
)

data class email (
    val created: String,
    val email: String,
    val id: String,
    val modified: String,
    val tag: String,
    val verified: String,
)

data class emailListResponse
    (
    val emails: List<email>,
    val success: Boolean,
    //val errors: List<String>,
    //val messages: List<String>,
    val result_info: resultInfo
)

fun fetchEmails(token: String, page: Int,context: Context, callback: (emailListResponse?) -> Unit) {
    val sharedPref = context.getSharedPreferences("cfer", Context.MODE_PRIVATE)
    val account_identifier = sharedPref.getString("userId", "null") ?: "null"
    if (account_identifier == "null") {
        callback(null)
        return
    }

    "https://api.cloudflare.com/client/v4\n" +
            "/accounts/$account_identifier/email/routing/addresses"
        .httpGet()
        .header("Authorization" to "Bearer $token")
        .header("Content-Type" to "application/json")
        .header("Accept" to "application/json")
        .responseObject(emailListResponseDeserializer()) { _, _, result ->
            when (result) {
                is com.github.kittinunf.result.Result.Failure -> {
                    val ex = result.getException()
                    callback(null)
                }
                is com.github.kittinunf.result.Result.Success -> {
                    val data = result.get()
                    callback(data)
                }
            }
        }
}

class emailListResponseDeserializer : ResponseDeserializable<emailListResponse> {
    override fun deserialize(content: String): emailListResponse =
        Gson().fromJson(content, emailListResponse::class.java)
}