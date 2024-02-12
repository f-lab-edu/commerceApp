package com.example.commerceapp.domain.repository

import com.example.commerceapp.data.remote.model.product.Brand
import com.example.commerceapp.data.remote.model.product.NaverShoppingCategory
import com.example.commerceapp.data.remote.model.product.ProductDetail
import com.example.commerceapp.data.remote.model.product.Tag
import com.example.commerceapp.data.remote.response.ProductSearchResponse
import com.example.commerceapp.data.remote.response.SearchResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun searchProduct(searchOptions: Map<String, String?>): Flow<ProductSearchResponse>
    fun getProductDetail(id: String): Flow<ProductDetail>
    fun getCategories(id: String? = null, name: String? = null): Flow<NaverShoppingCategory>
    fun getBrands(id: String? = null, name: String? = null): Flow<List<Brand>>
    fun getTags(searchOptions: Map<String, String?>): Flow<List<Tag>>
}