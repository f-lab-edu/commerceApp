package com.example.commerceapp.data.remote.model.mapper

import com.example.commerceapp.data.remote.model.UserDto
import com.example.commerceapp.domain.model.user.User

object UserMapper {
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
            dto.wishList.map { ProductPreviewMapper.mapToEntity(it) }
        )
    }
}