package com.example.commerceapp.ui

import com.example.commerceapp.domain.model.product.ProductHandler
import com.example.commerceapp.domain.model.product.ProductPreviewHandler
import com.example.commerceapp.domain.repository.ProductRepository
import com.example.commerceapp.domain.usecases.product.GetProductUseCase
import com.example.commerceapp.domain.usecases.product.SearchProductByBrandUseCase
import com.example.commerceapp.domain.usecases.product.SearchProductByCategoryUseCase
import com.example.commerceapp.domain.usecases.product.SearchProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

    @Provides
    fun provideSearchProducts(
        repository: ProductRepository,
        errorHandler: ProductPreviewHandler
    ): SearchProductsUseCase {
        return SearchProductsUseCase(repository, errorHandler)
    }

    @Provides
    fun provideSearchByBrandProducts(
        repository: ProductRepository,
        errorHandler: ProductPreviewHandler
    ): SearchProductByBrandUseCase {
        return SearchProductByBrandUseCase(repository, errorHandler)
    }

    @Provides
    fun provideSearchByBrandCategory(
        repository: ProductRepository,
        errorHandler: ProductPreviewHandler
    ): SearchProductByCategoryUseCase {
        return SearchProductByCategoryUseCase(repository, errorHandler)
    }

    @Provides
    fun provideProduct(
        repository: ProductRepository,
        errorHandler: ProductHandler
    ): GetProductUseCase {
        return GetProductUseCase(repository, errorHandler)
    }

}
