package com.example.commerceapp.domain.model.product

import com.example.commerceapp.domain.model.Attribute
import com.example.commerceapp.domain.model.Brand
import com.example.commerceapp.domain.model.Category
import com.example.commerceapp.domain.model.Image
import com.example.commerceapp.domain.model.Tag

data class Product(
    val id: String? = null,
    val attributes: List<Attribute>? = emptyList(),
    val averageReviewScore: Double = 0.0,
    val isBest: Boolean = false,
    val brand: Brand? = null,
    val contentText: String = "",
    val images: List<Image>? = emptyList(),
    val isNewItem: Boolean = false,
    val menus: List<String>? = emptyList(),
    val discountPrice: Int = 0,
    val discountRate: Int = 0,
    val modelNumber: String? = null,
    val name: String? = null,
    val category: Category? = null,
    val nvMid: Long? = null,
    val productNo: String? = null,
    val salePrice: Int = 0,
    val soldOut: Boolean = false,
    val standardColors: List<Any>? = emptyList(),
    val standardSizes: List<Any>? = emptyList(),
    val tags: List<Tag> = emptyList(),
    val totalReviewCount: Int = 0,
    val totalSaleCount: Int = 0,
    val unionCategories: List<Any> = emptyList(),
)