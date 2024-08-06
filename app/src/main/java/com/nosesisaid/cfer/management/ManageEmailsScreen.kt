package com.nosesisaid.cfer.management

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageEmailsScreen(navController: NavController) {

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
            total_count = 2
        ),
        errors = emptyList<CloudflareError>(),
        messages = emptyList<CloudflareMessage>()
    )
    var isLoading by remember { mutableStateOf(true) }
    var emails by remember { mutableStateOf(emptyList<email>()) }
    val context = LocalContext.current
//    fetchEmails(2, context) {
//        if (it == null) {
//            println("Failed to fetch emails.")
//            return@fetchEmails
//        }
//        emails = it.result
//    }

    LaunchedEffect(Unit) {
        fetchEmails(1, context) { response ->
            if (response != null) {
                emails = response.result
            } else {
                //TODO
            }
            isLoading = false
        }
    }

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Scaffold (
topBar = { TopAppBar(
    title = { Text(text = "Emails") },
    colors = topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary
    ))},
        bottomBar = { CFERNavigationBar(
            isEmailsSelected = true,
            nav = navController
        ) },
        floatingActionButton = {
                               FloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add email")
            }

        },
        content = { cpadding ->
            Column(
                modifier = Modifier
                    .padding(cpadding)
                    .padding(12.dp)
                    .fillMaxWidth(),


            )
            {

                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Email",modifier= Modifier
                            .padding(16.dp)
                            .width(160.dp),
                            textAlign = TextAlign.Center
                        )
                        Text("State",modifier= Modifier
                            .padding(16.dp)
                            .width(50.dp))
                        Text("Delete",modifier= Modifier
                            .padding(16.dp)
                            .width(90.dp))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            content
                            = {
                                items(emails) { email ->
                                    ElevatedCard(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 10.dp),
                                        shape = RoundedCornerShape(20.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                email.email,
                                                Modifier
                                                    .padding(16.dp)
                                                    .width(160.dp),
                                                maxLines = 1
                                            )
                                            Text(
                                                email.verified,
                                                Modifier.padding(16.dp).width(50.dp),
                                                maxLines = 1
                                            )
                                            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = "Delete"
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        )

                    }
                }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        modifier = Modifier.fillMaxHeight(),
                        sheetState = sheetState,
                        onDismissRequest = { showBottomSheet = false }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(32.dp),
                            verticalArrangement = Arrangement.Top

                        ) {

                            Text(
                                "Add a new Target Email to your account.",

                                style = MaterialTheme.typography.titleLarge
                            )
                            OutlinedTextField(value = "",
                                onValueChange = {},
                                label = { Text("Email adress")},

                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                                Text("Add Email")
                            }
                        }

                    }
                }

            }
        }
        )
}

