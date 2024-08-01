package com.example.commerceapp.data.remote

import com.example.commerceapp.data.remote.model.cart.CartDto
import com.example.commerceapp.data.remote.model.mapper.CartMapper
import com.example.commerceapp.domain.model.cart.Cart
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.repository.CartRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val cartMapper: CartMapper
) : CartRepository<CartRepository.CartRequestParam> {

    override suspend fun <CartRequestParam> getCartList(
        requestParam: CartRequestParam
    ): Flow<List<Cart>> {
        val param = requestParam as CartParam
        return firestore.collection("cart").document(param.userId).collection("cartItem")
            .snapshots()
            .map { convertToCartList(it.toObjects(CartDto::class.java)) }
    }

    override suspend fun addToCart(requestParam: RequestParam): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromCart(requestParam: RequestParam): Flow<String> {
        TODO("Not yet implemented")
    }

    data class CartParam(
        val userId: String,
        val page: Int,
        val perPage: Int
    ) : CartRepository.CartRequestParam

    private fun convertToCartList(dtos: List<CartDto>): List<Cart> {
        return dtos.map { cartMapper.mapToEntity(it) }
    }
}