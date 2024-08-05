package com.example.commerceapp.data.remote

import com.example.commerceapp.data.remote.model.cart.CartDto
import com.example.commerceapp.data.remote.model.mapper.CartMapper
import com.example.commerceapp.domain.model.cart.Cart
import com.example.commerceapp.domain.repository.CartRepository
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val cartMapper: CartMapper
) : CartRepository<CartRepository.CartRequestParam> {

    override suspend fun <CartRequestParam> getCartList(
        requestParam: CartRequestParam
    ): Flow<List<Cart>> {
        val param = (requestParam as CartParam)
        return firestore.collection("cart").document(param.userId).collection("cartItem")
            .snapshots()
            .map { convertToCartList(it.toObjects(CartDto::class.java)) }
    }

    override suspend fun <CartRequestParam> addToCart(
        requestParam: CartRequestParam
    ): Flow<String> {

        return flow {
            val param = (requestParam as CartUpdateParam)
            val collectionRef =
                FirebaseFirestore.getInstance().collection("cart").document("testId")
                    .collection("cartItem")
            try {
                firestore.runTransaction { transaction ->
                    val data =
                        transaction.get(collectionRef.document(param.productNo))
                            .toObject(CartDto::class.java)
                    if (data != null) {
                        transaction.update(
                            collectionRef.document(param.productNo),
                            "amount",
                            FieldValue.increment(1)
                        )
                    } else {
                        val newItem = hashMapOf("no" to param.productNo, "amount" to 1)
                        transaction.set(collectionRef.document(param.productNo), newItem)
                    }
                }.await()
                emit(requestParam.productNo)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override suspend fun <CartUpdateParam> removeFromCart(
        requestParam: CartUpdateParam
    ): Flow<String> {
        return flow {
            val param = (requestParam as CartRepositoryImpl.CartUpdateParam)
            val collectionRef =
                FirebaseFirestore.getInstance().collection("cart").document("testId")
                    .collection("cartItem")
            try {
                firestore.runTransaction { transaction ->
                    val data =
                        transaction.get(collectionRef.document(param.productNo))
                            .toObject(CartDto::class.java)
                    if (data != null) {
                        if (data.amount > 1) {
                            transaction.update(
                                collectionRef.document(param.productNo),
                                "amount",
                                FieldValue.increment(-1)
                            )
                        } else transaction.delete(collectionRef.document(param.productNo))
                    }
                }.await()
                emit(requestParam.productNo)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    data class CartParam(
        val userId: String,
        val page: Int,
        val perPage: Int
    ) : CartRepository.CartRequestParam

    data class CartUpdateParam(
        val userId: String,
        val productNo: String
    ) : CartRepository.CartRequestParam

    private fun convertToCartList(dtos: List<CartDto>): List<Cart> {
        return dtos.map { cartMapper.mapToEntity(it) }
    }
}