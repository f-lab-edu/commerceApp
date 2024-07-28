package com.example.commerceapp.domain.usecases.wishlist

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.WishListRepository
import com.example.commerceapp.domain.response.Response
import kotlinx.coroutines.flow.Flow

data class RemoveFromWishListUseCase(
    private val repository: WishListRepository,
    private val loginExceptionHandler: ErrorHandler
)  {
     suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<Response, Error>> =
        repository.removeFromWishList(parameters).mapToResultEntity(loginExceptionHandler)
}