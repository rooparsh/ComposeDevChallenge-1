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
package com.rooparsh.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Breed(
    @SerialName("name") val name: String? = null,
    @SerialName("bred_for") val bredFor: String? = null,
    @SerialName("breed_group") val breedGroup: String? = null,
    @SerialName("life_span") val lifeSpan: String? = null,
    @SerialName("origin") val origin: String? = null,
    @SerialName("image") val image: Image? = null,
    @SerialName("temperament") val temperament: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("weight") val weight: Unit? = null,
    @SerialName("height") val height: Unit? = null,
)

@Serializable
data class Image(@SerialName("url") val url: String? = null)

@Serializable
data class Unit(@SerialName("metric") val metric: String)
