package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.Brand
import com.example.commerceapp.domain.model.Category
import com.example.commerceapp.domain.model.common.request.ProductSearchParam
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.model.product.ProductPreview
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun productSearchByBrand(keyword: String): Flow<List<ProductPreview>>
    suspend fun productSearchByCategory(keyword: String): Flow<List<ProductPreview>>

    suspend fun searchProduct(param: ProductSearchParam): Flow<List<ProductPreview>>
    suspend fun searchCategories(keyword: String): Flow<List<Category>>
    suspend fun searchBrands(keyword: String): Flow<List<Brand>>

    suspend fun getProduct(id: String): Flow<Product>
    suspend fun getProductPreview(lists: List<Long>): Flow<List<ProductPreview>>
    suspend fun getAllCategory(): Flow<List<Category>>
    suspend fun getAllBrand(): Flow<List<Brand>>
}