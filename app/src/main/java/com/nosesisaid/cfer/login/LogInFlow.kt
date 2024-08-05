package com.nosesisaid.cfer.login

import android.content.Context
import com.google.gson.Gson
import fuel.Fuel
import fuel.get
import java.io.Reader
import com.google.gson.
import com.google.gson.JsonDeserializer

class resultTokenValidatonResponse {
    val id: String = ""
    val status: String = ""
}

class TokenValidatonResponse {
    val success: Boolean = false
    val errors: List<String> = emptyList()
    val messages: List<String> = emptyList()
    val result: resultTokenValidatonResponse = resultTokenValidatonResponse()
}

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
    val res = Fuel.get("https://api.cloudflare.com/client/v4/user/tokens/verify", headers = mapOf("Authorization" to "Bearer $token")).body


    return TODO("Provide the return value")
}

fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

class TokenValidationDeserializer  {
    fun deserialize(reader:Reader): TokenValidatonResponse {
        return Gson().fromJson(reader, TokenValidatonResponse::class.java)
    }
}