package com.example.commerceapp.domain.usecases.base

import com.example.commerceapp.domain.model.RequestParam
import com.example.commerceapp.domain.model.ResultEntity
import kotlinx.coroutines.flow.Flow

abstract class BaseFlowUseCase<R> : BaseUseCase<RequestParam, Flow<ResultEntity<R>>>()