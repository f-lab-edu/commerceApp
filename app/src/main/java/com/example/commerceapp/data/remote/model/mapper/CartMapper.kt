package com.example.commerceapp.data.remote.model.mapper

import com.example.commerceapp.data.remote.model.cart.CartDto
import com.example.commerceapp.domain.model.cart.Cart

class CartMapper {
    fun mapToEntity(dto: CartDto): Cart {
        return Cart(
            no = dto.no,
            amount = dto.amount
        )
    }

    fun mapToDto(cart: Cart): CartDto {
        return CartDto(
            no = cart.no,
            amount = cart.amount
        )
    }
}