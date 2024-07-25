package com.example.commerceapp.domain.usecases.payment

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.PaymentRepository
import com.example.commerceapp.domain.response.Response
import kotlinx.coroutines.flow.Flow

class PaymentUseCase(
    private val repository: PaymentRepository,
    private val loginExceptionHandler: ErrorHandler
)  {
    suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<Response, Error>> =
        repository.cancelPayment(parameters).mapToResultEntity(loginExceptionHandler)

}