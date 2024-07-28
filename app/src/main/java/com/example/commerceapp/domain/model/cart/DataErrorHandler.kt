package com.example.commerceapp.domain.model.cart

import com.example.commerceapp.domain.extension.toErrorType
import com.example.commerceapp.domain.model.common.DataError
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.InvalidDataException
import com.example.commerceapp.domain.model.common.ResultEntity

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