package com.example.commerceapp.domain.usecases.base

abstract class BaseUseCase<in P, R> {
    abstract suspend operator fun invoke(parameters: P): R
}