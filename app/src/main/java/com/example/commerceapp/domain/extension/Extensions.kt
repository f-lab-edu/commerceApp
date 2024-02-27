package com.example.commerceapp.domain.extension

import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.ErrorType
import com.example.commerceapp.domain.model.common.ErrorType.CONFLICT
import com.example.commerceapp.domain.model.common.ErrorType.FORBIDDEN
import com.example.commerceapp.domain.model.common.ErrorType.INVALID
import com.example.commerceapp.domain.model.common.ErrorType.NETWORK_ERROR
import com.example.commerceapp.domain.model.common.ErrorType.NOT_FOUND
import com.example.commerceapp.domain.model.common.ErrorType.SERVER_ERROR
import com.example.commerceapp.domain.model.common.ErrorType.UNAUTHORIZED
import com.example.commerceapp.domain.model.common.ErrorType.UNKNOWN_ERROR
import com.example.commerceapp.domain.model.common.ResultEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toErrorType(): ErrorType {
    return when (this) {
        is HttpException -> this.mapToErrorType(code())
        is ConnectException, is SocketTimeoutException, is UnknownHostException -> NETWORK_ERROR
        else -> UNKNOWN_ERROR
    }
}

fun HttpException.mapToErrorType(code: Int): ErrorType = when (code) {
    400 -> INVALID
    401 -> UNAUTHORIZED
    404 -> NOT_FOUND
    403 -> FORBIDDEN
    409 -> CONFLICT
    500 -> SERVER_ERROR
    else -> UNKNOWN_ERROR

}

fun <T> Flow<T>.mapToResultEntity(handler: ErrorHandler): Flow<ResultEntity<T>> =
    map {
        try {
            ResultEntity.Success(it)
        } catch (t: Throwable) {
            handler.handle(t)
        }
    }