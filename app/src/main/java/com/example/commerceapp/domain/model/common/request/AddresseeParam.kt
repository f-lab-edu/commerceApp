package com.example.commerceapp.domain.model.common.request

data class AddresseeParam(
    val uid: String,
    val addressName: String,
    val receiverName: String,
    val zipCode: String,
    val baseAddress: String,
    val detailAddress: String,
    val roadNameYn: String,
    val tel: String,
    val primary: String
)
