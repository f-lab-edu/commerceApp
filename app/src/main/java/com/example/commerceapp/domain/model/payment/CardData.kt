package com.example.commerceapp.domain.model.payment

data class CardData(
    val cardApproveNo: String,
    val cardCompany: String,
    val cardCompanyCode: String,
    val cardNo: String,
    val cardQuota: String,
    val receiptUrl: String
)