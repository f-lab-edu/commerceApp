package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.common.request.AddresseeParam
import com.example.commerceapp.domain.model.common.request.UserUpdateParam
import com.example.commerceapp.domain.model.user.Addressee
import com.example.commerceapp.domain.model.user.User
import com.example.commerceapp.domain.model.user.UserPreview
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getMyInfo(id: String): Flow<User>
    suspend fun getMyPreviewInfo(id: String): Flow<UserPreview>
    suspend fun updateMyInfo(param: UserUpdateParam): Flow<User>
    suspend fun addAddressee(param: AddresseeParam): Flow<Addressee>
}