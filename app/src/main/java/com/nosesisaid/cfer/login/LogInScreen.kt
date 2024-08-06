package com.nosesisaid.cfer.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun SingInScreen(navController: NavController) {
    val context = LocalContext.current

    val sharedPref = context.getSharedPreferences("cfer", Context.MODE_PRIVATE)

    if (sharedPref.contains("email") && sharedPref.contains("APIKey") && sharedPref.contains("userId")) {
        navController.navigate("manageEmails")
    }


    val API_KEY_GENERATOR_ENDPOINT = "https://dash.cloudflare.com/profile/api-tokens?permissionGroupKeys=%5B%7B%22key%22%3A%22email_routing_address%22%2C%22type%22%3A%22edit%22%7D%2C%7B%22key%22%3A%22email_routing_rule%22%2C%22type%22%3A%22edit%22%7D%5D&name=CFEMTtoken&accountId=*&zoneId=all";
    val uriHandler = LocalUriHandler.current

    var zoneId by remember { mutableStateOf("") }
    var APIKey by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }


    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold (
        snackbarHost = { SnackbarHost(hostState = snackbarHostState,
            modifier = Modifier.padding(
            WindowInsets.ime.asPaddingValues()),) },
        topBar= {
            TopAppBar(title = { Text(text = "Log in into CFER") },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            actions = {
                IconButton(onClick = { uriHandler.openUri("https://github.com/v1ctorio/cloudlfare-email-manager") }) {
                    Icon(
                        imageVector = Icons.Filled.Build,
                        contentDescription = "source code"
                    )

                }
            }
            )

        },
        content = { cpadding ->
            Column(
                modifier = Modifier
                    .padding(cpadding)
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            {

                ElevatedButton(onClick = { uriHandler.openUri(API_KEY_GENERATOR_ENDPOINT) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),


                ) {

                    Row {
                        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "")
                        Text(text = "Get your API Key", style = TextStyle(fontSize = 16.sp), modifier = Modifier.padding(horizontal = 8.dp))
                    }
                }
                Text(text = "Login to your CF account using an API key.")
                Text(text = "Email", modifier = Modifier.padding(12.dp), style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = zoneId,
                    label={
                        Text("Zone ID")
                          },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange ={
                        zoneId = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                OutlinedTextField(
                    value = APIKey,
                    label={
                        Text("API Key")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontFamily = FontFamily.Monospace),
                    onValueChange ={
                        APIKey = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii)
                )
                OutlinedTextField(
                    value = userId,
                    label={
                        Text("User ID")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontFamily = FontFamily.Monospace),
                    onValueChange ={
                        userId = it
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {

                    saveLogInData(zoneId, APIKey, userId, context) { result ->
                        if (result == "success") {
                            navController.navigate("manageEmails")
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(message = result, duration = SnackbarDuration.Short)
                            }
                        }
                    }

                                 }, modifier = Modifier.fillMaxWidth()) {
                    Text("Log in")
                }
            }

        }
    )

    fun onLogInSubmit() {
        navController.navigate("manageEmails")
    }
}