package com.rooparsh.data.network.api

import com.rooparsh.data.network.model.Breed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {
    @GET(GET_BREEDS)
    suspend fun getDogBreeds(
        @Query("attach_breed") attachBreed: Int = 1,
        @Query("page") page: Int? = 0,
        @Query("limit") limit: Int = 20
    ): Response<List<Breed>>
}