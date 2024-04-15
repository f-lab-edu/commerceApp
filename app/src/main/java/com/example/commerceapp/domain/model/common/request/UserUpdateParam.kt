package com.example.commerceapp.domain.model.common.request

data class UserUpdateParam(
    val id: String,
    val name: String,
    val age: Int,
    val email: String,
    val phoneNo: String,
)