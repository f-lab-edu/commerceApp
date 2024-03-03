package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.Response
import com.example.commerceapp.domain.model.user.UserPreview
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(requestParam: RequestParam): Flow<UserPreview>
    fun logout(requestParam: RequestParam): Flow<Response>
    fun signup(requestParam: RequestParam): Flow<Response>
}