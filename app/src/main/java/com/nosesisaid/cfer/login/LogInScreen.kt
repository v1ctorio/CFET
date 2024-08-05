package com.nosesisaid.cfer.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun SingInScreen(navController: NavController) {
    val API_KEY_GENERATOR_ENDPOINT = "https://dash.cloudflare.com/profile/api-tokens?permissionGroupKeys=%5B%7B%22key%22%3A%22email_routing_address%22%2C%22type%22%3A%22edit%22%7D%2C%7B%22key%22%3A%22email_routing_rule%22%2C%22type%22%3A%22edit%22%7D%5D&name=CFEMTtoken&accountId=*&zoneId=all";

    val uriHandler = LocalUriHandler.current
    Scaffold (
        topBar= {
            TopAppBar(title = { Text(text = "Log in into CFER") },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
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
                    .padding(12.dp)
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
                    value = "email@example.com",
                    label={
                        Text("Email")
                          },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange ={}
                )
                OutlinedTextField(
                    value = "bunch of number and letters and stuff",
                    label={
                        Text("API Key")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontFamily = FontFamily.Monospace),
                    onValueChange ={}
                )
                OutlinedTextField(
                    value = "hiiii",
                    label={
                        Text("Account ID")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontFamily = FontFamily.Monospace),
                    onValueChange ={}
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Log in")
                }
            }

        }
    )
}