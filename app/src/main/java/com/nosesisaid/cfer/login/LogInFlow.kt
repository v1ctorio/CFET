package com.nosesisaid.cfer.login

import android.content.Context
import com.google.gson.Gson
import fuel.Fuel
import fuel.get
import java.io.Reader
import com.google.gson.JsonDeserializer
import fuel.httpGet
import gson

data class resultTokenValidatonResponse (
    val id: String,
    val status: String
)

data class TokenValidatonResponse(
    val success: Boolean,
    val errors: List<String> ,
    val messages: List<String> ,
    val result: resultTokenValidatonResponse ,
)

fun SaveLogInData(email: String, APIKey: String, userId:String,context: Context):String {



    if (email.isEmpty() || APIKey.isEmpty() || userId.isEmpty()) {
        return "You must fill all the fields"
    }
    if (!isEmailValid(email)) {
        return "Invalid email"
    }
    if (!validate_token(APIKey)) {
        return "Invalid API Key"
    }

    val sharedPref = context.getSharedPreferences("cfer", Context.MODE_PRIVATE)
    with (sharedPref.edit()) {
        putString("email", email)
        putString("APIKey", APIKey)
        putString("userId", userId)
        apply()
    }
    return "success"
}

suspend fun validate_token(token:String ):Boolean{
    "https://api.cloudflare.com/client/v4/user/tokens/verify".httpGet(
        headers = mapOf("Authorization " to "Bearer $token")
    ).responseObject()

    return TODO("Provide the return value")
}

fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

