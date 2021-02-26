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
package com.example.androiddevchallenge

import android.app.Activity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.view.PuppyDetails
import com.example.androiddevchallenge.ui.view.PuppyItem
import com.rooparsh.data.model.Puppy
import com.rooparsh.data.repo.PuppyRepo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyApp() {
    var selectedPuppy by remember { mutableStateOf<Puppy?>(null) }
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (selectedPuppy != null) {
                    selectedPuppy = null
                } else {
                    (context as? Activity)?.finish()
                }
            }
        }
    }

    DisposableEffect(key1 = backDispatcher) {
        backDispatcher.onBackPressedDispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }

    Surface(color = MaterialTheme.colors.background) {
        selectedPuppy?.let { puppy ->
            PuppyDetails(puppy = puppy)
        } ?: run {
            Column(
                Modifier
                    .padding(16.dp)
            ) {
                Text(text = "PUPPY FINDER", style = MaterialTheme.typography.overline)
                Text(text = "Let's find you a 🐶!", style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.size(16.dp))
                PuppyGrid {
                    selectedPuppy = it
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PuppyGrid(onPuppyClick: (Puppy) -> Unit) {
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(items = PuppyRepo.puppies) { pup ->
            Box(Modifier.padding(8.dp)) {
                PuppyItem(puppy = pup, modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clickable {
                        onPuppyClick(pup)
                    })
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
