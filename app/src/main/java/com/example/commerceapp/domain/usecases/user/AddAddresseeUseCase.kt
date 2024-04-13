package com.example.commerceapp.domain.usecases.user

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.UserRepository
import com.example.commerceapp.domain.response.Response
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

class AddAddresseeUseCase(
    private val repository: UserRepository<RequestParam>,
    private val loginExceptionHandler: ErrorHandler
) : BaseFlowUseCase<Response>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<Response>> =
        repository.addAddressee(parameters).mapToResultEntity(loginExceptionHandler)
}