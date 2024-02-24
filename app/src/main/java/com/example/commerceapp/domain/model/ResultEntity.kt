package com.example.commerceapp.domain.model

sealed interface ResultEntity<T> {
    data class Success<T>(val data: T) : ResultEntity<T>
    data class Error<T>(val error: ErrorType, val message: String) : ResultEntity<T>
}

