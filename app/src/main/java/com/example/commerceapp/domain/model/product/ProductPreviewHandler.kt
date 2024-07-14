package com.example.commerceapp.domain.model.product

import com.example.commerceapp.domain.extension.toErrorType
import com.example.commerceapp.domain.model.common.DataError
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.InvalidDataException
import com.example.commerceapp.domain.model.common.ResultEntity

class ProductPreviewHandler : ErrorHandler {
    override fun <ProductPreview, Error> handle(throwable: Throwable): ResultEntity.Error<ProductPreview, DataError> {
        return ResultEntity.Error(
            error = throwable.toErrorType()
        )
    }

    override fun <D, E> handle(throwable: InvalidDataException): ResultEntity<D, Error> {
        TODO("Not yet implemented")
    }
}