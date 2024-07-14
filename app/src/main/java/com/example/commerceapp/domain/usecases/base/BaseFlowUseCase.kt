package com.example.commerceapp.domain.usecases.base

import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ResultEntity
import kotlinx.coroutines.flow.Flow

abstract class BaseFlowUseCase<R> : BaseUseCase<Flow<ResultEntity<R, Error>>>()