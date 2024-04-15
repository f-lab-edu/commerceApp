package com.example.commerceapp.data.remote.model

import com.example.commerceapp.domain.model.OrderPreview
import com.example.commerceapp.domain.model.user.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val id: String,
    val name: String,
    val age: Int,
    val email: String,
    val phoneNo: String,
    val addressee: List<AddresseeDto> = emptyList(),
    val orderList: List<OrderPreview> = emptyList(),
    val wishList: List<ProductPreviewDto> = emptyList()
) {
    fun mapToEntity(): User {
        return User(
            id,
            name,
            age,
            email,
            phoneNo,
            addressee.map { it.mapToEntity() },
            orderList,
            wishList.map { it.mapToEntity() }
        )
    }
}