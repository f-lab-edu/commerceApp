package com.example.commerceapp.data.remote.model.mapper

import com.example.commerceapp.data.remote.model.UserPreviewDto
import com.example.commerceapp.domain.model.user.UserPreview

class UserPreviewMapper {
    fun mapToEntity(dto: UserPreviewDto): UserPreview {
        return UserPreview(
            dto.id,
            dto.name,
            dto.email,
            dto.profile
        )
    }
}