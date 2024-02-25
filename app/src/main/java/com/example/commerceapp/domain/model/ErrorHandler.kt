package com.example.commerceapp.domain.model

interface ErrorHandler {
    fun <T>handle(throwable: Throwable): ResultEntity.Error<T>
}