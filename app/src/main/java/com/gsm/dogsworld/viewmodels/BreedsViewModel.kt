package com.gsm.dogsworld.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsm.dogsworld.models.Breed
import com.gsm.dogsworld.network.NetworkMonitor
import com.gsm.dogsworld.repository.DogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val repository: DogsRepository,
    private val savedStateHandle: SavedStateHandle,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    val breeds: StateFlow<List<Breed>>
        get() = repository.breeds

    val isConnected: StateFlow<Boolean> get() = networkMonitor.isConnected

    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collectLatest { isConnected ->
                if (isConnected && breeds.value.isEmpty()) {
                    try {
                        val categoryName =
                            savedStateHandle.get<String>("category") ?: "Small Dog Breeds"
                        repository.getBreedsByCategory(categoryName)
                    } catch (e: Exception) {
                        Log.e("BreedsViewModel", "Something went wrong!")
                    }
                }
            }
        }
    }
}