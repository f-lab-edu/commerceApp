package com.example.commerceapp.domain.model.common

interface ErrorHandler {
    fun <T>handle(throwable: Throwable): ResultEntity.Error<T>
}