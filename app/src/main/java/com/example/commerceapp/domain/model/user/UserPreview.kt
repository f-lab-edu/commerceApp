package com.example.commerceapp.domain.model.user

import com.example.commerceapp.domain.model.Image

data class UserPreview(
    val id: String,
    val name: String,
    val email: String,
    val profile: Image
)