package com.nosesisaid.cfer.management

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign

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
        bottomBar = { CFERNavigationBar() },
        floatingActionButton = {
                               FloatingActionButton(onClick = { /*TODO*/ }) {
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
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row {

                        Text("chatskdfasfadsfads@nosesisaid.com",
                            Modifier
                                .padding(16.dp)
                                .width(160.dp), maxLines = 1)
                        Text(text = "Verified",
                            Modifier
                                .padding(16.dp)
                                .width(50.dp)
                        )
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.width(90.dp)) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription ="Delte email" )
                        }
                    }
                }
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row {

                        Text("hichat@nosesisaid.com",
                            Modifier
                                .padding(16.dp)
                                .width(160.dp), maxLines = 1)
                        Text(text = "Pending",
                            Modifier
                                .padding(16.dp)
                                .width(50.dp)
                        )
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.width(90.dp)) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription ="Delte email" )
                        }
                    }
                }
            }
        }
        )
}