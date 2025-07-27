package com.gsm.dogsworld.models

import com.google.gson.annotations.SerializedName

data class Breed(
    override val name: String,
    @SerializedName("image")
    override val imageUrl: String,
    val origin: String,
    val lifespan: String,
    val temperament: List<String>,
    val height: String,
    val weight: String,
    val group: String,
    val description: String
) : DisplayableItem