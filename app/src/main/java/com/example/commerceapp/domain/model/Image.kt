package com.example.commerceapp.domain.model

data class Image(
    val imageClassType: String,
    val imageUrl: String,
    val representativeImage: Boolean = false,
    val name: String
)