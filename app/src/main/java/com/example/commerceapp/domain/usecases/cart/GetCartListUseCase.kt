package com.example.commerceapp.domain.usecases.cart

import com.example.commerceapp.data.remote.model.mapper.CartItemMapper
import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.cart.Cart
import com.example.commerceapp.domain.model.cart.CartItem
import com.example.commerceapp.domain.model.common.DataErrorHandler
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.repository.CartRepository
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

data class GetCartListUseCase(
    private val cartRepository: CartRepository<CartRepository.CartRequestParam>,
    private val productRepository: ProductRepository,
    private val dataErrorHandler: DataErrorHandler,
    private val cartItemMapper: CartItemMapper
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun invoke(
        parameters: CartRepository.CartRequestParam
    ): Flow<ResultEntity<List<CartItem>, Error>> =
        cartRepository.getCartList(parameters)
            .flatMapConcat {
                getCartItem(it)
            }.mapToResultEntity(dataErrorHandler)

    private suspend fun getCartItem(list: List<Cart>): Flow<List<CartItem>> {
        val lists = list.map { it.no.toLong() }
        return productRepository.getProductPreview(lists)
            .map { previewToCartItem(it, list) }
    }

    private fun previewToCartItem(
        productPreviews: List<ProductPreview>,
        list: List<Cart>
    ): List<CartItem> {
        return productPreviews.map { preview ->
            cartItemMapper.mapToCartItem(preview, list.find { it.no == preview.no }?.amount ?: 0)
        }
    }
}