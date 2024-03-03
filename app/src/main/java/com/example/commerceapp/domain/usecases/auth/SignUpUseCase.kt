package com.example.commerceapp.domain.usecases.auth

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.Response
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.AuthRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

class SignUpUseCase(
    private val authRepository: AuthRepository,
    private val errorHandler: ErrorHandler
) : BaseFlowUseCase<Response>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<Response>> =
        authRepository.signup(parameters).mapToResultEntity(errorHandler)
}