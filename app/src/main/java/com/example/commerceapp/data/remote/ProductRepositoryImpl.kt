package com.example.commerceapp.data.remote

import com.example.commerceapp.domain.model.Brand
import com.example.commerceapp.domain.model.Category
import com.example.commerceapp.domain.model.Tag
import com.example.commerceapp.domain.model.common.RequestParam
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
    ProductRepository<ProductRepositoryImpl.ProductParam> {

    class ProductParam(
        val queryString: String? = null
    ) : RequestParam

    override fun searchProduct(requestParam: ProductParam): Flow<List<ProductPreview>> {
        val collectionRef = firestore.collection("products")
        val query = if (requestParam.queryString.isNullOrBlank()) {
            collectionRef
        } else {
            collectionRef
                .whereArrayContainsAny("name", listOf("*${requestParam.queryString}*"))
                .whereArrayContainsAny("brand", listOf("*${requestParam.queryString}*"))
                .whereArrayContainsAny("tags", listOf("*${requestParam.queryString}*"))
        }

        return query.snapshots().map { it.toObjects(ProductPreview::class.java) }
    }

    override fun getProduct(requestParam: ProductParam): Flow<Product> {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("products")
        return collectionRef.whereEqualTo("_id", requestParam.queryString).snapshots()
            .mapNotNull { snapshot ->  // null이면 무시
                snapshot.firstOrNull()?.toObject(Product::class.java)
            }
    }

    override fun getCategories(id: String?, name: String?): Flow<Category> {
        val collectionRef = firestore.collection("category")
        val query = if (id.isNullOrBlank()) {
            collectionRef
        } else {
            collectionRef
                .whereEqualTo("name", id)
        }
        return query.snapshots().mapNotNull { it.toObjects(Category::class.java).firstOrNull() }
    }

    override fun getBrands(id: String?, name: String?): Flow<List<Brand>> {
        val collectionRef = firestore.collection("brand")
        val query = if (id.isNullOrBlank()) {
            collectionRef
        } else {
            collectionRef
                .whereArrayContainsAny("name", listOf("*${name}*"))
        }
        return query.snapshots().mapNotNull { it.toObjects(Brand::class.java) }
    }

    /**
     * TODO : 추후 구현 예정
     */
    override fun getTags(searchOptions: Map<String, String?>): Flow<List<Tag>> {
        TODO("Not yet implemented")
    }
}