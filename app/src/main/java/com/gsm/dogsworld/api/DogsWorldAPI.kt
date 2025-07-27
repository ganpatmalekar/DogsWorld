package com.gsm.dogsworld.api

import com.gsm.dogsworld.models.Categories
import com.gsm.dogsworld.models.CategoryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface DogsWorldAPI {

    @GET("/v3/b/687cd199d039d559a1689485?meta=false")
    suspend fun getDogCategories(): Response<Categories>

    @GET("/v3/b/687cd199d039d559a1689485?meta=false")
    suspend fun getBreedsByCategory(
        @Header("X-JSON-Path") categoryName: String
    ): Response<List<CategoryItem>>
}