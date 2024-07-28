package com.example.commerceapp.data.remote

import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.repository.CartRepository
import com.example.commerceapp.domain.usecases.cart.GetCartListUseCase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : CartRepository<GetCartListUseCase.RequestParam> {

    override suspend fun getCartList(
        requestParam: GetCartListUseCase.RequestParam
    ): Flow<HashMap<String, Long>> {
        return firestore.collection("cart").document(requestParam.userId).snapshots()
            .map { origin ->
                val hashMap = HashMap<String, Long>()
                origin.data?.keys?.forEach { key -> hashMap[key] = origin[key] as Long }
                hashMap
            }
    }

    override suspend fun addToCart(requestParam: RequestParam): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromCart(productNo: RequestParam): Flow<String> {
        TODO("Not yet implemented")
    }
}