package com.example.commerceapp.data.remote

import com.example.commerceapp.data.remote.model.mapper.CartMapper
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
    ): Flow<HashMap<String, Long>> {
        val param = requestParam as CartParam
        return firestore.collection("cart").document(param.userId)
            .snapshots()
            .map { origin ->
                val hashMap = HashMap<String, Long>()
                origin.data?.keys?.forEach { key -> hashMap[key] = origin[key] as Long }
                hashMap
            }
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
}



