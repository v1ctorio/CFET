package com.nosesisaid.cfer.management

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import com.nosesisaid.cfer.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarningElementDeletion(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
    target: String,
    target_id: String,
    isEmail: Boolean
) {
    AlertDialog(
        icon = {
            Icon(painter = painterResource(id = R.drawable.baseline_delete_forever_24), contentDescription = "Delete icon")
        },
        title = {
            Text(text = if(isEmail)"Are you sure you want to delete this email?" else "Are you sure you want to delete this route?")
        },
        text = {
            Text(text = buildAnnotatedString {
                append("If you click confirm,")
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(" $target ")
                }
                append("will be removed from your account.")
            }
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(target_id)
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}