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

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rooparsh.data.network.model.Breed
import com.rooparsh.data.network.model.DataResult
import com.rooparsh.data.repo.DogRepo

class BreedSource(private val dogRepo: DogRepo) : PagingSource<Int, Breed>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        return when (val dogResponse = dogRepo.getDogBreedFromServer(params.key)) {
            is DataResult.Success -> {
                LoadResult.Page(
                    data = dogResponse.body,
                    prevKey = null,
                    nextKey = (params.key ?: 0) + 1
                )
            }

            is DataResult.Failure -> LoadResult.Error(dogResponse.throwable)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
