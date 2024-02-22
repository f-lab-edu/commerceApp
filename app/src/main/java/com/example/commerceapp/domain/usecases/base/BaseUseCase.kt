package com.example.commerceapp.domain.usecases.base

import com.example.commerceapp.domain.model.RequestEntity

abstract class BaseUseCase<T> {
    abstract suspend fun execute(requestEntity: RequestEntity): T
}