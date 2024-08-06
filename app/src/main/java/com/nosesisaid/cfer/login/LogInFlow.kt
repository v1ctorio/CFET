package com.nosesisaid.cfer.login

import android.content.Context
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result
import com.google.gson.Gson

data class ResultTokenValidationResponse(
    val id: String,
    val status: String
)

data class TokenValidationResponse(
    val success: Boolean,
    val errors: List<String>,
  //  val messages: List<String>,
    val result: ResultTokenValidationResponse
)



class TokenValidationResponseDeserializer : com.github.kittinunf.fuel.core.ResponseDeserializable<TokenValidationResponse> {
    override fun deserialize(content: String): TokenValidationResponse =
        Gson().fromJson(content, TokenValidationResponse::class.java)
}

fun saveLogInData(zoneId: String, APIKey: String, userId: String, context: Context, callback: (String) -> Unit) {
    if (zoneId.isEmpty() || APIKey.isEmpty() || userId.isEmpty()) {
        callback("You must fill all the fields")
        return
    }

    validateToken(APIKey) { isValid ->
        if (!isValid) {
            callback("Invalid API Key")
            return@validateToken
        }
        val sharedPref = context.getSharedPreferences("cfer", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("zoneId", zoneId)
            putString("APIKey", APIKey)
            putString("userId", userId)
            apply()
        }
        callback("success")
    }
}

fun validateToken(token: String, callback: (Boolean) -> Unit) {
    "https://api.cloudflare.com/client/v4/user/tokens/verify"
        .httpGet()
        .header("Authorization" to "Bearer $token")
        .responseObject(TokenValidationResponseDeserializer()) { _, _, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    println("Error: ${ex.message}")
                    callback(false)
                }
                is Result.Success -> {
                    val response = result.get()
                    println("Token validation success: ${response.success}")
                    callback(response.success)
                }
            }
        }
}
