package com.example.commerceapp.data.remote

import com.example.commerceapp.data.remote.model.mapper.CartItemMapper
import com.example.commerceapp.data.remote.model.mapper.ProductMapper
import com.example.commerceapp.data.remote.model.mapper.ProductPreviewMapper
import com.example.commerceapp.data.remote.model.mapper.UserMapper
import com.example.commerceapp.data.remote.model.mapper.UserPreviewMapper
import com.example.commerceapp.domain.model.product.ProductHandler
import com.example.commerceapp.domain.model.product.ProductPreviewHandler
import com.example.commerceapp.domain.repository.ProductRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideFirebaseStore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideProductMapper() = ProductMapper()

    @Provides
    fun provideProductPreviewMapper() = ProductPreviewMapper()

    @Provides
    fun provideUserMapper() = UserMapper()

    @Provides
    fun provideUserPreviewMapper() = UserPreviewMapper()

    @Provides
    fun provideCartItemMapper() = CartItemMapper()

    @Provides
    fun provideProductRepository(
        firestore: FirebaseFirestore,
        productMapper: ProductMapper,
        productPreviewMapper: ProductPreviewMapper
    ): ProductRepository {
        return ProductRepositoryImpl(firestore, productMapper, productPreviewMapper)
    }

    @Provides
    fun providePreviewErrorHandler(): ProductPreviewHandler {
        return ProductPreviewHandler()
    }

    @Provides
    fun provideProductErrorHandler(): ProductHandler {
        return ProductHandler()
    }
}