package com.example.commerceapp.domain.model.product

import com.example.commerceapp.domain.extension.toErrorType
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.ResultEntity

class ProductPreviewHandler : ErrorHandler {
    override fun <ProductPreview> handle(throwable: Throwable): ResultEntity.Error<ProductPreview> {
        return ResultEntity.Error(
            error = throwable.toErrorType(),
            message = throwable.message ?: throwable.stackTraceToString()
        )
    }
}