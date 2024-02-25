package com.example.commerceapp.domain.model

interface RequestParam

interface PagingRequestParam {
    val page: Int
    val pagePerSize: Int
}