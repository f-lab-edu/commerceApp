package com.example.commerceapp.domain.model.common

/**
 * paging된 응답 처리는?
 */
sealed interface ResultEntity<T> {
    data class Success<T>(val data: T) : ResultEntity<T>
    data class Error<T>(val error: ErrorType, val message: String) : ResultEntity<T>
}

