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
package com.rooparsh.data.repo

import com.rooparsh.data.network.api.DogApi
import com.rooparsh.data.network.model.Breed
import com.rooparsh.data.network.model.DataResult
import com.rooparsh.data.util.safeApiCall

interface DogRepo {

    fun filterData(query: String)

    suspend fun getDogBreedFromServer(page: Int?): DataResult<List<Breed>>
}

class DogRepositoryImpl(private val dogApi: DogApi) : DogRepo {

    override fun filterData(query: String) {
    }

    override suspend fun getDogBreedFromServer(page: Int?): DataResult<List<Breed>> =
        safeApiCall { dogApi.getDogBreeds(page = page) }
}
