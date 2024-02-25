package com.example.commerceapp.domain

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Result<T>(data = data)
    class Error<T>(message: String) : Result<T>(message = message)
}