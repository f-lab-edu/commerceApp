package com.example.commerceapp.domain.model.payment

data class VBankData(
    val bankAccount: String,
    val bankCode: String,
    val bankName: String,
    val bankUsername: String,
    val cashReceiptNo: String,
    val expiredAt: String,
    val senderName: String
)