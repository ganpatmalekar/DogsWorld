package com.gsm.dogsworld.repository

import android.util.Log
import com.gsm.dogsworld.api.DogsWorldAPI
import com.gsm.dogsworld.models.Breed
import com.gsm.dogsworld.models.CategoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DogsRepository @Inject constructor(private val dogsWorldAPI: DogsWorldAPI) {

    private val _categories = MutableStateFlow<List<CategoryItem>>(emptyList())
    val categories: StateFlow<List<CategoryItem>>
        get() = _categories

    private val _breeds = MutableStateFlow<List<Breed>>(emptyList())
    val breeds: StateFlow<List<Breed>>
        get() = _breeds

    suspend fun getDogsCategories() {
        try {
            val response = dogsWorldAPI.getDogCategories()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { _categories.emit(it.categories) }
            }
        } catch (e: Exception) {
            Log.e("DogsRepository", "getDogsCategories: Error: ${e.localizedMessage}")
        }
    }

    suspend fun getBreedsByCategory(categoryName: String) {
        try {
            val response = dogsWorldAPI.getBreedsByCategory(
                "$..categories[?(@.categoryName==\"$categoryName\")]"
            )
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { _breeds.emit(it[0].breeds) }
            }
        } catch (e: Exception) {
            Log.e("DogsRepository", "getBreedsByCategory: Error: ${e.localizedMessage}")
        }
    }
}