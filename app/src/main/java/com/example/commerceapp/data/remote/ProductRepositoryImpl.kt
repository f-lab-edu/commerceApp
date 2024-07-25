package com.example.commerceapp.data.remote

import com.example.commerceapp.data.BRAND_COLLECTION_PATH
import com.example.commerceapp.data.CATEGORY_COLLECTION_PATH
import com.example.commerceapp.data.PRODUCT_COLLECTION_PATH
import com.example.commerceapp.data.remote.model.ProductDto
import com.example.commerceapp.data.remote.model.ProductPreviewDto
import com.example.commerceapp.data.remote.model.mapper.ProductMapper
import com.example.commerceapp.data.remote.model.mapper.ProductPreviewMapper
import com.example.commerceapp.domain.model.Brand
import com.example.commerceapp.domain.model.Category
import com.example.commerceapp.domain.model.common.DataError
import com.example.commerceapp.domain.model.common.FireStoreNoDataException
import com.example.commerceapp.domain.model.common.InvalidDataException
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

class ProductRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val productMapper: ProductMapper,
    private val productPreMapper: ProductPreviewMapper
) : ProductRepository {

    override suspend fun productSearchByBrand(keyword: String): Flow<List<ProductPreview>> {
        return firestore.collection(PRODUCT_COLLECTION_PATH)
            .whereEqualTo("brand", keyword).snapshots()
            .map { convertToProductList(it.toObjects(ProductPreviewDto::class.java)) }
    }

    override suspend fun productSearchByCategory(keyword: String): Flow<List<ProductPreview>> {
        return firestore.collection(PRODUCT_COLLECTION_PATH)
            .whereEqualTo("category", keyword).snapshots()
            .map { convertToProductList(it.toObjects(ProductPreviewDto::class.java)) }
    }

    override suspend fun searchProduct(param: ProductSearchParam): Flow<List<ProductPreview>> {
        return if (param.keyword.isNotBlank()) {
            firestore.collection(PRODUCT_COLLECTION_PATH).snapshots()
                .map { querySnapshot ->
                    convertToProductList(querySnapshot.toObjects(ProductPreviewDto::class.java))
                        .filter { product -> product.name.contains(param.keyword) }
                }
        } else {
            firestore.collection(PRODUCT_COLLECTION_PATH).limit(20).snapshots()
                .map { convertToProductList(it.toObjects(ProductPreviewDto::class.java)) }
        }
    }

    override suspend fun getProduct(id: String): Flow<Product> {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection(PRODUCT_COLLECTION_PATH)
        return collectionRef.whereEqualTo("no", id.toLong()).snapshots()
            .mapNotNull { snapshot ->  // null이면 무시
                snapshot.firstOrNull()?.toObject(ProductDto::class.java)
                    ?.let { productMapper.mapToProduct(it) } ?: throw InvalidDataException(DataError.VALIDATION.EMPTY)
            }
    }

    override suspend fun searchCategories(keyword: String): Flow<List<Category>> {
        return firestore.collection(CATEGORY_COLLECTION_PATH)
            .whereArrayContainsAny("name", listOf("*${keyword}*")).snapshots()
            .mapNotNull { it.toObjects(Category::class.java) }
    }

    override suspend fun searchBrands(keyword: String): Flow<List<Brand>> {
        return firestore.collection(BRAND_COLLECTION_PATH)
            .whereArrayContainsAny("name", listOf("*${keyword}*")).snapshots()
            .mapNotNull { it.toObjects(Brand::class.java) }
    }

    override suspend fun getAllCategory(): Flow<List<Category>> {
        return firestore.collection(CATEGORY_COLLECTION_PATH).snapshots()
            .mapNotNull { it.toObjects(Category::class.java) }
    }

    override suspend fun getAllBrand(): Flow<List<Brand>> {
        return firestore.collection(BRAND_COLLECTION_PATH).snapshots()
            .mapNotNull { it.toObjects(Brand::class.java) }
    }

    private fun convertToProductList(dtos: List<ProductPreviewDto>): List<ProductPreview> {
        return dtos.map { productPreMapper.mapToEntity(it) }
    }
}