package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.Brand
import com.example.commerceapp.domain.model.Category
import com.example.commerceapp.domain.model.Tag
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.model.product.ProductPreview
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun searchProduct(requestParam: RequestParam): Flow<List<ProductPreview>>
    fun getProductDetail(requestParam: RequestParam): Flow<Product>
    fun getCategories(id: String? = null, name: String? = null): Flow<Category>
    fun getBrands(id: String? = null, name: String? = null): Flow<List<Brand>>
    fun getTags(searchOptions: Map<String, String?>): Flow<List<Tag>>
}