package com.example.commerceapp.domain.usecases.user

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.user.UserPreview
import com.example.commerceapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserPreviewUseCase(
    private val repository: UserRepository,
    private val userExceptionHandler: ErrorHandler
) {
    suspend operator fun invoke(id: String): Flow<ResultEntity<UserPreview, Error>> =
        repository.getMyPreviewInfo(id).mapToResultEntity(userExceptionHandler)

}