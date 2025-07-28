package com.gsm.dogsworld.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsm.dogsworld.models.CategoryItem
import com.gsm.dogsworld.network.NetworkMonitor
import com.gsm.dogsworld.repository.DogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val repository: DogsRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    val categories: StateFlow<List<CategoryItem>>
        get() = repository.categories

    val isConnected: StateFlow<Boolean> get() = networkMonitor.isConnected

    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collectLatest { isConnected ->
                if (isConnected && categories.value.isEmpty()) {
                    try {
                        repository.getDogsCategories()
                    } catch (e: Exception) {
                        Log.e("DogsViewModel", "Something went wrong!")
                    }
                }
            }
        }
    }
}