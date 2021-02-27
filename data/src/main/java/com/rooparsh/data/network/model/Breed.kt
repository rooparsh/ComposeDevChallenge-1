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
