package com.example.commerceapp.domain.model.common

interface ErrorHandler {
    fun <D,E>handle(throwable: Throwable): ResultEntity<D, Error>
    fun <D,E>handle(throwable: InvalidDataException): ResultEntity<D, Error>
}