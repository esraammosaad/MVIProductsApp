package com.example.productsapp.utils.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomDivider() {
    HorizontalDivider(
        color = Color(0xFFD3D3D3),
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(1.dp)
    )
}