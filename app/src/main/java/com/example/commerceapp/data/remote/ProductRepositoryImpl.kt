package com.example.commerceapp.data.remote

import com.example.commerceapp.data.remote.model.ProductDto
import com.example.commerceapp.data.remote.model.ProductPreviewDto
import com.example.commerceapp.data.remote.model.mapper.ProductMapper
import com.example.commerceapp.data.remote.model.mapper.ProductPreviewMapper
import com.example.commerceapp.domain.model.Brand
import com.example.commerceapp.domain.model.Category
import com.example.commerceapp.domain.model.common.request.ProductSearchParam
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.repository.ProductRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    ProductRepository {

    override suspend fun productSearchByBrand(keyword: String): Flow<List<ProductPreview>> {
        return firestore.collection("products")
            .whereEqualTo("brand", keyword).snapshots()
            .map { convertToProductList(it.toObjects(ProductPreviewDto::class.java)) }
    }

    override suspend fun productSearchByCategory(keyword: String): Flow<List<ProductPreview>> {
        return firestore.collection("products")
            .whereEqualTo("category", keyword).snapshots()
            .map { convertToProductList(it.toObjects(ProductPreviewDto::class.java)) }
    }

    override suspend fun searchProduct(searchParam: ProductSearchParam): Flow<List<ProductPreview>> {
        return if (searchParam.keyword.isNotBlank()) {
            firestore.collection("products")
                .whereArrayContainsAny("name", listOf("*${searchParam.keyword}*"))
                .whereArrayContainsAny("brand", listOf("*${searchParam.keyword}*"))
                .whereArrayContainsAny("tags", listOf("*${searchParam.keyword}*"))
                .limit(20).snapshots()
                .map { convertToProductList(it.toObjects(ProductPreviewDto::class.java)) }
        } else {
            firestore.collection("products").limit(20).snapshots()
                .map { convertToProductList(it.toObjects(ProductPreviewDto::class.java)) }
        }
    }

    override suspend fun getProduct(id: String): Flow<Product> {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("products")
        return collectionRef.whereEqualTo("_id", id).snapshots()
            .mapNotNull { snapshot ->  // null이면 무시
                snapshot.firstOrNull()?.toObject(ProductDto::class.java)
                    ?.let { ProductMapper.mapToProduct(it) }
            }
    }

    override suspend fun searchCategories(keyword: String): Flow<List<Category>> {
        return firestore.collection("category")
            .whereArrayContainsAny("name", listOf("*${keyword}*")).snapshots()
            .mapNotNull { it.toObjects(Category::class.java) }
    }

    override suspend fun searchBrands(keyword: String): Flow<List<Brand>> {
        return firestore.collection("brand")
            .whereArrayContainsAny("name", listOf("*${keyword}*")).snapshots()
            .mapNotNull { it.toObjects(Brand::class.java) }
    }

    override suspend fun getAllCategory(): Flow<List<Category>> {
        return firestore.collection("category").snapshots()
            .mapNotNull { it.toObjects(Category::class.java) }
    }

    override suspend fun getAllBrand(): Flow<List<Brand>> {
        return firestore.collection("brand").snapshots()
            .mapNotNull { it.toObjects(Brand::class.java) }
    }

    private fun convertToProductList(dtos: List<ProductPreviewDto>): List<ProductPreview> {
        return dtos.map { ProductPreviewMapper.mapToEntity(it) }
    }
}