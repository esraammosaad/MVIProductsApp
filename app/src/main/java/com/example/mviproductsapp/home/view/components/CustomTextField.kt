package com.example.mviproductsapp.home.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun CustomTextField(
    value: MutableState<String>,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {
        value.value = it
    },
) {
    TextField(
        label = {
            Text(
                text = label,
                color = Color.Black,
            )
        },
        enabled = true,
        modifier = Modifier
            .fillMaxWidth(),
        value = value.value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(25),
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = leadingIcon,
    )
}