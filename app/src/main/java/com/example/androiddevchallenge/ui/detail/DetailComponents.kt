package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Detail(heading: String, detail: String, modifier: Modifier = Modifier) {
    Column(modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(heading, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.secondary)
        Text(
            detail,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.secondaryVariant
        )
    }
}

@Composable
fun Section(heading: String, detail: String, modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(16.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(heading, style = MaterialTheme.typography.h5, color = MaterialTheme.colors.secondary)
        Text(
            detail,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.secondaryVariant
        )
    }
}