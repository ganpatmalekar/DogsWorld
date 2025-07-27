package com.gsm.dogsworld.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsm.dogsworld.models.CategoryItem
import com.gsm.dogsworld.repository.DogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(private val repository: DogsRepository) : ViewModel() {

    val categories: StateFlow<List<CategoryItem>>
        get() = repository.categories

    init {
        viewModelScope.launch {
            repository.getDogsCategories()
        }
    }
}