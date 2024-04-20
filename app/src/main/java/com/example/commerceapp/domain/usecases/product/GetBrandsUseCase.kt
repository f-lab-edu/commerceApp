package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.data.remote.ProductRepositoryImpl
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(
    private val repository: ProductRepository<RequestParam>
) {
//    operator fun invoke(id: String?,name: String): Flow<Result<List<Brand>>> =
//        repository.getBrands(id,name).map {
//
//        }

}