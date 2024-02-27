package com.example.commerceapp.domain.model.common

interface RequestParam

interface PagingRequestParam {
    val page: Int
    val pagePerSize: Int
}