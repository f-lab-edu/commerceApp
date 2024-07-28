package com.example.commerceapp.domain.model.common

import com.example.commerceapp.domain.extension.toErrorType

class DataErrorHandler : ErrorHandler {
    override fun <D, Error> handle(throwable: Throwable): ResultEntity.Error<D, DataError> {
        return ResultEntity.Error(
            error = throwable.toErrorType()
        )
    }

    override fun <D, E> handle(throwable: InvalidDataException): ResultEntity<D, DataError> {
        TODO("Not yet implemented")
    }
}