package com.nosesisaid.cfer.management.api

import android.content.Context
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.google.gson.JsonObject


//FOR DEBUG
val exampleResponse = emailListResponse(
    result = listOf(
        email(
            created = "2024-08-01T12:00:00Z",
            email = "example1@example.com",
            id = "1",
            modified = "2024-08-02T12:00:00Z",
            tag = "newsletter",
            verified = "true"
        ),
        email(
            created = "2024-08-01T13:00:00Z",
            email = "example2@example.com",
            id = "2",
            modified = "2024-08-02T13:00:00Z",
            tag = "promotion",
            verified = "false"
        )
    ),
    success = true,
    result_info = resultInfo(
        count = 2,
        page = 1,
        per_page = 10,
        total_count = 2,
    ),
    errors = emptyList<CloudflareError>(),
    messages = emptyList<CloudflareMessage>()
)
//ENd


//Types

data class CloudflareError(
    val code: Int,
    val message: String,
)

data class CloudflareMessage(
    val code: Int,
    val message: String,
)

data class resultInfo(
    val count: Int,
    val page: Int,
    val per_page: Int,
    val total_count: Int,
)

data class email(
    val created: String,
    val email: String,
    val id: String,
    val modified: String,
    val tag: String,
    val verified: String?,
)

data class emailListResponse
    (
    val result: List<email>,
    val success: Boolean,
    val errors: List<CloudflareError>,
    val messages: List<CloudflareMessage>,
    val result_info: resultInfo
)

fun fetchEmails(page: Int, context: Context, callback: (emailListResponse?) -> Unit) {
    val sharedPref = context.getSharedPreferences("cfer", Context.MODE_PRIVATE)
    val account_identifier = sharedPref.getString("userId", "null") ?: "null"
    val token = sharedPref.getString("APIKey", "null") ?: "null"


    println("$token,$account_identifier")
    if (account_identifier == "null") {
        callback(null)
        return
    }

    var a =
        "https://api.cloudflare.com/client/v4/accounts/$account_identifier/email/routing/addresses"
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
                        //val emailListResponses: List<emailListResponse> = Gson().fromJson(data, object : TypeToken<List<emailListResponse>>() {}.type)
                        //val emailListResponse = emailListResponses.firstOrNull()
                        val jsonObject = Gson().fromJson(data, JsonObject::class.java)
                        val emailListResponse =
                            Gson().fromJson(jsonObject, emailListResponse::class.java)
                        println(emailListResponse)
                        callback(emailListResponse)
                    }
                }
            }

}


//
//class EmailListResponseDeserializer : ResponseDeserializable<emailListResponse> {
//    override fun deserialize(content: String): emailListResponse =
//        //Gson().fromJson(content, emailListResponse)
//}