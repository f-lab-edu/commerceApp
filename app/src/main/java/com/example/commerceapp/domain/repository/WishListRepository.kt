package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.response.Response
import kotlinx.coroutines.flow.Flow

interface WishListRepository {
    fun getWishList(requestParam: RequestParam): Flow<List<ProductPreview>>
    fun addToWishList(requestParam: RequestParam): Flow<Response>
    fun removeFromWishList(requestParam: RequestParam): Flow<Response>
}