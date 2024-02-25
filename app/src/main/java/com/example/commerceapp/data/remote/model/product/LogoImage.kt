package com.example.commerceapp.data.remote.model.product

data class LogoImage(
    val _id: String,
    val fileSize: Int,
    val height: Int,
    val imageClassType: String,
    val imageName: String,
    val imageUrl: String,
    val representativeImage: Boolean,
    val sortOrder: Int,
    val width: Int
)