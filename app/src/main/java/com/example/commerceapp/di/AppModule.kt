package com.example.commerceapp.di

import com.example.commerceapp.data.repository.ProductRepositoryImpl
import com.example.commerceapp.domain.repository.ProductRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

//    @Provides
//    @Singleton
//    fun provideItemRepository(itemRemoteDataSource: ItemRemoteDataSource): ItemRepository {
//        return ItemRepositoryImpl(itemRemoteDataSource)
//    }

    @Provides
    @Singleton
    fun provideProductRepository(firestore: FirebaseFirestore): ProductRepository {
        return ProductRepositoryImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore{
        return Firebase.firestore
    }
}