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
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.rooparsh.data.network.model.Breed

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    query: String,
    items: LazyPagingItems<Breed>,
    listState: LazyListState,
    onItemClickListener: (Breed) -> Unit,
    onQueryChanged: (String) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                title = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Dog Finder")
                    }
                }
            )
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                Modifier.padding(horizontal = 16.dp)
            ) {
                SearchBar(query) { onQueryChanged(it) }
                DogList(items, listState) {
                    onItemClickListener(it)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun DogList(
    listItems: LazyPagingItems<Breed>,
    listState: LazyListState,
    onItemClick: (Breed) -> Unit
) {
    LazyColumn(state = listState) {
        items(listItems) { pup ->
            pup?.let {
                Box(Modifier.padding(8.dp)) {
                    HomeListItem(
                        breed = pup,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(pup)
                            }
                    )
                }
            }
        }
    }
}
