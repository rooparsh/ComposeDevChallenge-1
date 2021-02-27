package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.rooparsh.data.network.model.Breed

@ExperimentalFoundationApi
@Composable
fun HomeScreen(items: LazyPagingItems<Breed>, listState: LazyListState, listener: (Breed) -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.surface,
                title = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Dog Finder")
                    }
                })
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                Modifier.padding(horizontal = 16.dp)
            ) {
                DogList(items, listState) {
                    listener(it)
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
        item { Text(text = "Find a suitable 🐶!", style = MaterialTheme.typography.h4) }
        items(listItems) { pup ->
            pup?.let {
                Box(Modifier.padding(8.dp)) {
                    HomeListItem(breed = pup, modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(pup)
                        })
                }
            }
        }
    }
}