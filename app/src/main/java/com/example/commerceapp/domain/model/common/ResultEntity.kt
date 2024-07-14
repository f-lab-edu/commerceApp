package com.example.commerceapp.domain.model.common

typealias RootError = Error

sealed interface ResultEntity<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D) : ResultEntity<D,E>
    data class Error<out D, out E: RootError>(val error: DataError)  : ResultEntity<D,E>
}

