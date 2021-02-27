package com.example.androiddevchallenge.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rooparsh.data.network.model.Breed
import com.rooparsh.data.repo.DogRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dogRepo: DogRepo) : ViewModel() {

    val query = mutableStateOf("")

    fun fetchDogBreeds(): Flow<PagingData<Breed>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            initialKey = 1
        ) {
            BreedSource(dogRepo)
        }.flow.cachedIn(viewModelScope)
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
        dogRepo.filterData(query)
    }

}