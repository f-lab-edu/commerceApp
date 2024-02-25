package com.example.commerceapp.data.repository

import com.example.commerceapp.data.remote.model.product.Brand
import com.example.commerceapp.data.remote.model.product.NaverShoppingCategory
import com.example.commerceapp.data.remote.model.product.Product
import com.example.commerceapp.data.remote.model.product.ProductDetail
import com.example.commerceapp.data.remote.model.product.Tag
import com.example.commerceapp.data.remote.response.ProductSearchResponse
import com.example.commerceapp.domain.enntity.product.ProductDetailEntity
import com.example.commerceapp.domain.enntity.product.ProductEntity
import com.example.commerceapp.domain.repository.ProductRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(val firestore: FirebaseFirestore) :
    ProductRepository {
    private var lastItemKey: String? = null // 마지막 항목의 키

    override fun searchProduct(searchOptions: Map<String, String?>): Flow<ProductSearchResponse> =
        callbackFlow {
            val page = searchOptions["start"]!!.toInt()
            val queryStr = searchOptions["query"]
            val pageSize = searchOptions["display"]!!.toLong()
            val listenerRegistration =
                firestore.collection("products").limit(pageSize)
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            close(error) // 혹은 close() 후 return
                        }
                        val productsList =
                            value?.documents?.mapNotNull { it.toObject(Product::class.java) }
                        if (!productsList.isNullOrEmpty()) {
                            lastItemKey = productsList.last().id
                            val mappedList = productsList.map { it.mapToProductEntity() }.toList()
                            trySend(
                                ProductSearchResponse(
                                    items = mappedList,
                                    start = page
                                )
                            ).isSuccess // Flow에 데이터를 emit
                        }
                    }
            awaitClose { listenerRegistration.remove() }
        }

    override fun getProductDetail(id: String): Flow<ProductDetailEntity> = callbackFlow {
        val listenerRegistration = firestore.collection("products").limit(1)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    close(error)
                }

                val productsList =
                    value?.documents?.mapNotNull { it.toObject(ProductDetail::class.java) }
                if (productsList != null) {
                    Timber.tag("TAG").d("productsList => %s", productsList.firstOrNull())
                    productsList.firstOrNull()
                        ?.let { trySend(it.mapToProductDetailEntity()).isSuccess }
                }
            }
        awaitClose { listenerRegistration.remove() }
    }

    override fun getBrands(id: String?, name: String?) = callbackFlow {
        val dbRef = firestore.collection("brand")
        if (id.isNullOrEmpty()) dbRef.whereEqualTo("_id", id)
        if (name.isNullOrEmpty()) dbRef.whereEqualTo("name", name)
        val listenerRegistration = dbRef
            .addSnapshotListener { value, error ->
                if (error != null) {
                    close(error)
                }

                val brand = value?.documents?.first()?.toObject(Brand::class.java)
                if (brand != null) {
                    Timber.d("productsList => %s", brand)
                    trySend(listOf(brand)).isSuccess
                }
            }
        awaitClose { listenerRegistration.remove() }
    }

    override fun getCategories(id: String?, name: String?) = callbackFlow {
        val dbRef = firestore.collection("category")
        if (id.isNullOrEmpty()) dbRef.whereEqualTo("_id", id)
        if (name.isNullOrEmpty()) dbRef.whereEqualTo("name", name)
        val listenerRegistration = dbRef
            .addSnapshotListener { value, error ->
                if (error != null) {
                    close(error)
                }

                val category =
                    value?.documents?.first()?.toObject(NaverShoppingCategory::class.java)
                Timber.d("category => %s", category)
                if (category != null) {
                    trySend(category).isSuccess
                }
            }
        awaitClose { listenerRegistration.remove() }
    }

    private fun setQueryToRef(searchOptions: Map<String, String?>): CollectionReference {
        val pageSize = searchOptions["display"]!!.toInt()
        val queryStr = searchOptions["query"]
        val page = searchOptions["start"]!!.toInt()

        val product_ref = firestore.collection("products")
        var category: NaverShoppingCategory? = null
        var brand: Brand? = null
        val filters = mutableListOf<Filter>()
        if (!queryStr.isNullOrEmpty()) {
            filters.add(Filter.equalTo("name", queryStr))
            runBlocking {
                val jobs = listOfNotNull(
                    CoroutineScope(Dispatchers.IO).async {
                        getBrands(name = queryStr).collect {
                            brand = it.first()
                            filters.add(Filter.equalTo("brand", brand!!.name))
                        }
                    },
                    CoroutineScope(Dispatchers.IO).async {
                        getCategories(name = queryStr).collect {
                            category = it
                            filters.add(Filter.equalTo("category", category!!.name))
                        }
                    })
                jobs.awaitAll()
            }
        }

        Timber.tag("TAG").d("set filters " + filters.size)
        if (filters.isNotEmpty()) product_ref.where(Filter.or(*filters.toTypedArray()))
        if (page > 1 && !lastItemKey.isNullOrEmpty()) product_ref.orderBy("_id")
            .startAfter(lastItemKey)
        product_ref.limit(pageSize.toLong())
        return product_ref
    }

    /**
     * TODO : 검색을 tag나 다른 데이터로 찾는 기능 추가 필요.
     */

    override fun getTags(searchOptions: Map<String, String?>): Flow<List<Tag>> {
        TODO("Not yet implemented")
    }

}