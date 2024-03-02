package com.example.commerceapp.domain.usecases.user.my

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.user.User
import com.example.commerceapp.domain.repository.UserRepository
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

class GetMyInfoUseCase(
    val repository: UserRepository,
    private val loginExceptionHandler: ErrorHandler
) : BaseFlowUseCase<User>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<User>> =
        repository.getMyInfo(parameters).mapToResultEntity(loginExceptionHandler)

}