package com.example.commerceapp.domain.usecases.auth

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.user.UserPreview
import com.example.commerceapp.domain.repository.AuthRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: AuthRepository,
    private val loginExceptionHandler: ErrorHandler
) : BaseFlowUseCase<UserPreview>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<UserPreview>> =
        repository.login(parameters).mapToResultEntity(loginExceptionHandler)
}