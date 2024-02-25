package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.Product.Brand
import com.example.commerceapp.domain.model.Product.Category
import com.example.commerceapp.domain.model.Product.Product
import com.example.commerceapp.domain.model.Product.ProductDetail
import com.example.commerceapp.domain.model.Product.Tag
import com.example.commerceapp.domain.model.RequestParam
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun searchProduct(requestParam: RequestParam): Flow<List<Product>>
    fun getProductDetail(requestParam: RequestParam): Flow<ProductDetail>
    fun getCategories(id: String? = null, name: String? = null): Flow<Category>
    fun getBrands(id: String? = null, name: String? = null): Flow<List<Brand>>
    fun getTags(searchOptions: Map<String, String?>): Flow<List<Tag>>
}