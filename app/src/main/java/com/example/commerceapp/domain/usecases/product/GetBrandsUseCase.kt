package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.data.remote.model.product.Brand
import com.example.commerceapp.domain.Result
import com.example.commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(
    val repository: ProductRepository
) {
//    operator fun invoke(id: String?,name: String): Flow<Result<List<Brand>>> =
//        repository.getBrands(id,name).map {
//
//        }

}