package com.example.commerceapp.domain.model.product

import com.example.commerceapp.domain.extension.toErrorType
import com.example.commerceapp.domain.model.common.DataError
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.InvalidDataException
import com.example.commerceapp.domain.model.common.ResultEntity

class ProductHandler : ErrorHandler {

    override fun <Product, E> handle(throwable: InvalidDataException): ResultEntity.Error<Product, DataError>{
        return ResultEntity.Error(
            error = throwable.type
        )
    }

    override fun <Product, E> handle(throwable: Throwable): ResultEntity.Error<Product, DataError> {
        return ResultEntity.Error(
            error = throwable.toErrorType()
        )
    }
}