package com.example.commerceapp.domain.model

data class Attribute(
    val id: String,
    val name: String,
    val values: List<Option> = emptyList()
)
