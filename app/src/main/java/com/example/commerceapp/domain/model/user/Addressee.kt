package com.example.commerceapp.domain.model.user

data class Addressee(
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
