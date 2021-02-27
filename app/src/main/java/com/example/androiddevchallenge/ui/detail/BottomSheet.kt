package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val START_TOP_PADDING = 160


@Composable
fun BottomSheet(scrollState: ScrollState, content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier
            .verticalScroll(scrollState)
            .padding(top = START_TOP_PADDING.dp)
            .background(
                MaterialTheme.colors.surface,
                RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            )
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(all = 32.dp)
    ) {
        content()
    }
}