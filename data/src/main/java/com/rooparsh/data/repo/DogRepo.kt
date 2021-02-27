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

    fun getDogBreed(page: Int = 0): Flow<ViewState<List<Breed>>>

    suspend fun getDogBreedFromServer(page: Int?): DataResult<List<Breed>>
}

class DogRepositoryImpl(private val dogApi: DogApi) : DogRepo {

    override fun getDogBreed(page: Int): Flow<ViewState<List<Breed>>> = flow {
        emit(ViewState.loading())
        when (val freshData = getDogBreedFromServer(page)) {
            is DataResult.Success -> {
                emit(ViewState.success(freshData.body))
            }
            is DataResult.Failure -> {
                emit(ViewState.error<List<Breed>>(freshData.throwable))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDogBreedFromServer(page: Int?): DataResult<List<Breed>> =
        safeApiCall { dogApi.getDogBreeds(page = page) }

}