package com.example.commerceapp.data.remote.model

import com.example.commerceapp.domain.model.user.UserPreview
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserPreviewDto(
    val id: String,
    val name: String,
    val email: String,
    val profile: String = ""
) {
    fun mapToEntity(): UserPreview {
        return UserPreview(id, name, email, profile)
    }
}