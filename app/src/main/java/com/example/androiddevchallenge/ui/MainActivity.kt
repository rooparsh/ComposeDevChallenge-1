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
package com.example.androiddevchallenge.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.androiddevchallenge.ui.detail.DetailScreen
import com.example.androiddevchallenge.ui.home.HomeScreen
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.rooparsh.data.network.model.Breed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                DogApp()
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun DogApp() {

        val navController: NavHostController = rememberNavController()
        val items = viewModel.fetchDogBreeds().collectAsLazyPagingItems()
        var selectedBreed by remember { mutableStateOf<Breed?>(null) }
        val listState = rememberLazyListState()

        NavHost(navController = navController, startDestination = Navigation.Home.title) {
            composable(Navigation.Home.title) {
                HomeScreen(
                    query = viewModel.query.value,
                    items = items,
                    listState = listState,
                    onItemClickListener = {
                        selectedBreed = it
                        navController.navigate(Navigation.Detail.title)
                    },
                    onQueryChanged = { viewModel.onQueryChanged(it) }
                )
            }

            composable(Navigation.Detail.title) {
                selectedBreed?.let {
                    DetailScreen(breed = it) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            DogApp()
        }
    }

    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            DogApp()
        }
    }
}
