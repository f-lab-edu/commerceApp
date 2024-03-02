package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.Order
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun login(requestParam: RequestParam): Flow<String>
    fun logout(requestParam: RequestParam): Flow<String>
    fun signup(requestParam: RequestParam): Flow<String>

    fun getMyInfo(requestParam: RequestParam): Flow<User>
    fun updateMyInfo(requestParam: RequestParam): Flow<String>

    fun getWishList(requestParam: RequestParam): Flow<List<Product>>
    fun addToWishList(requestParam: RequestParam): Flow<String>
    fun removeFromWishList(requestParam: RequestParam): Flow<String>

    fun getCartList(requestParam: RequestParam): Flow<List<Product>>
    fun addToCart(requestParam: RequestParam): Flow<String>
    fun removeFromCart(requestParam: RequestParam): Flow<String>

    fun getOrderList(requestParam: RequestParam): Flow<List<Order>>
    fun cancelOrder(requestParam: RequestParam): Flow<String>
    fun order(requestParam: RequestParam): Flow<Order>
    fun addAddressee(requestParam: RequestParam): Flow<String>
}