package com.example.commerceapp.domain.model.payment

import com.example.commerceapp.data.remote.model.payment.PaymentData

data class PaymentResult(
    val paymentData: PaymentData,
    val event: String
)