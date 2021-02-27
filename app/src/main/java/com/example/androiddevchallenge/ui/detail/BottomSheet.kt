/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
