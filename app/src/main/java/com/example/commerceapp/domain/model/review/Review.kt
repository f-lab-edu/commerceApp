package com.example.commerceapp.domain.model.review

data class Review(
    val id: String,
    val createdAt: String,
    val productNo: String,
    val productName: String,
    val productOrderNo: String,
    val reviewContent: String,
    val reviewScore: Long,
    val writerId: String,
    val writerProfileImageUrl: String
)