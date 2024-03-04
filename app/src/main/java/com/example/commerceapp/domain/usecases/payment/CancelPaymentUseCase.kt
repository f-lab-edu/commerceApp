package com.example.commerceapp.domain.usecases.payment

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.repository.PaymentRepository
import com.example.commerceapp.domain.response.Response
import com.example.commerceapp.domain.usecases.base.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

class CancelPaymentUseCase(
    private val repository: PaymentRepository,
    private val handler: ErrorHandler
) : BaseFlowUseCase<Response>() {
    override suspend fun invoke(parameters: RequestParam): Flow<ResultEntity<Response>> =
        repository.cancelPayment(parameters).mapToResultEntity(handler)

}