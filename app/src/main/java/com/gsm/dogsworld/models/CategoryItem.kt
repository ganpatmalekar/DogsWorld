package com.gsm.dogsworld.models

import com.google.gson.annotations.SerializedName

data class CategoryItem(
    val breeds: List<Breed>,
    @SerializedName("categoryName")
    override val name: String,
    @SerializedName("categoryImage")
    override val imageUrl: String
) : DisplayableItem