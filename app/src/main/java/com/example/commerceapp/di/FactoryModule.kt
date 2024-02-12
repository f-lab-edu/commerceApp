package com.example.commerceapp.di

import com.example.commerceapp.domain.repository.ProductRepository
import com.example.commerceapp.domain.usecases.product.GetBrandsUseCase
import com.example.commerceapp.domain.usecases.product.GetProductDetailUseCase
import com.example.commerceapp.domain.usecases.product.GetSearchProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FactoryModule {
//    @Provides
//    fun provideItemDataSource(itemApi: ItemApi, dispatcher: CoroutineDispatcher): ItemRemoteDataSource {
//        return ItemRemoteDataSource(itemApi,dispatcher)
//    }

    @Provides
    fun provideSearchProductsUseCase(repository: ProductRepository):GetSearchProductsUseCase{
        return GetSearchProductsUseCase(repository)
    }

    @Provides
    fun provideProductDetailUseCase(repository: ProductRepository):GetProductDetailUseCase{
        return GetProductDetailUseCase(repository)
    }

    @Provides
    fun provideGetBrandsUseCase(repository: ProductRepository): GetBrandsUseCase {
        return GetBrandsUseCase(repository)
    }


//    PaymentUseCase
//    GetReviewUseCase
//    CreateReviewUseCase
//    DeleteReviewUseCase
//    UpdateReviewUseCase
//    AddCartUseCase
//    CancelOrderUseCase
//    GetOrderList
//    LoginUseCase
//    OrderUseCase
//    RemoveCartUseCase
}