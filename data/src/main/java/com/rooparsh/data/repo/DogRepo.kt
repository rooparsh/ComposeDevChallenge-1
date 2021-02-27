package com.rooparsh.data.repo

import com.rooparsh.data.network.api.DogApi
import com.rooparsh.data.network.model.Breed
import com.rooparsh.data.network.model.DataResult
import com.rooparsh.data.util.ViewState
import com.rooparsh.data.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface DogRepo {


    fun filterData(query : String)

    suspend fun getDogBreedFromServer(page: Int?): DataResult<List<Breed>>
}

class DogRepositoryImpl(private val dogApi: DogApi) : DogRepo {


    override fun filterData(query: String) {

    }

    override suspend fun getDogBreedFromServer(page: Int?): DataResult<List<Breed>> =
        safeApiCall { dogApi.getDogBreeds(page = page) }

}