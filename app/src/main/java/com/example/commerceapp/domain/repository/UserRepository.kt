package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.user.User
import com.example.commerceapp.domain.model.user.UserPreview
import com.example.commerceapp.domain.response.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getMyInfo(requestParam: RequestParam): Flow<User>
    fun getMyPreviewInfo(requestParam: RequestParam): Flow<UserPreview>
    fun updateMyInfo(requestParam: RequestParam): Flow<Response>
    fun addAddressee(requestParam: RequestParam): Flow<Response>
}