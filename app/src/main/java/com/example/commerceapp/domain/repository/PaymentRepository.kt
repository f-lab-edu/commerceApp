package com.example.commerceapp.domain.repository

import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.response.Response
import kotlinx.coroutines.flow.Flow

/**
 * TODO : requestParam, Response type 변경
 */
interface PaymentRepository {
    fun payment(requestParam: RequestParam): Flow<Response>
    fun cancelPayment(requestParam: RequestParam): Flow<Response>
}