package com.example.commerceapp.domain.usecases.product

import com.example.commerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
//    operator fun invoke(id: String?,name: String): Flow<Result<List<Brand>>> =
//        repository.getBrands(id,name).map {
//
//        }

}