package com.example.commerceapp.domain.usecases.base

abstract class BaseUseCase<R> {
    abstract suspend operator fun invoke(): R
}