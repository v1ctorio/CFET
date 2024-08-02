package com.nosesisaid.cfer.management

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ManageEmailsScreen() {
    Scaffold (
topBar = { TopAppBar(
    title = { Text(text = "Emails") },
    colors = topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary
    ))},
        content = { cpadding ->
            Column(
                modifier = Modifier
                    .padding(cpadding)
                    .padding(12.dp)
                    .fillMaxWidth()
            )
            {
                Text("Manage Emails")
            }
        }
        )
}