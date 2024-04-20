package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.Category
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategories @Inject constructor(
    private val repository: ProductRepository,
    private val errorHandler: ErrorHandler
) {
    suspend fun invoke(): Flow<ResultEntity<List<Category>>> =
        repository.getAllCategory().mapToResultEntity(errorHandler)
}