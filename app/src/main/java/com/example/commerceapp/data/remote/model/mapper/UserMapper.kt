package com.example.commerceapp.data.remote.model.mapper

import com.example.commerceapp.data.remote.model.UserDto
import com.example.commerceapp.domain.model.user.User
import com.example.commerceapp.domain.model.user.UserPreview

class UserMapper {
    fun mapToEntity(dto: UserDto): User {
        return User(
            dto.id,
            dto.name,
            dto.age,
            dto.email,
            dto.phoneNo,
            dto.profile,
            dto.addressee.map { it.mapToEntity() },
            dto.orderList,
            dto.wishList.map { ProductPreviewMapper().mapToEntity(it) }
        )
    }

    fun mapToPreview(dto: UserDto):UserPreview{
        return UserPreview(
            id = dto.id,
            name = dto.name,
            email = dto.email,
            profile = dto.email
        )
    }
}