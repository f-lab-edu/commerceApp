package com.example.commerceapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.commerceapp.R


@Composable
fun VerticalDivider(height: Dp) {
    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.gray_01))
            .fillMaxWidth()
            .height(height)
            .padding(top = 8.dp, bottom = 8.dp)
    )
}