package com.nosesisaid.cfer.management


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun CFERNavigationBar() {

         NavigationBar {
             NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = { Icon(Icons.Filled.Search, contentDescription = "Routes managment") })
            NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = { Icon(Icons.Filled.Email, contentDescription = "Email tab") })
         }

}