package com.gsm.dogsworld.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsm.dogsworld.models.Breed
import com.gsm.dogsworld.repository.DogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val repository: DogsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val breeds: StateFlow<List<Breed>>
        get() = repository.breeds

    init {
        viewModelScope.launch {
            val categoryName = savedStateHandle.get<String>("category") ?: "Small Dog Breeds"
            repository.getBreedsByCategory(categoryName)
        }
    }
}