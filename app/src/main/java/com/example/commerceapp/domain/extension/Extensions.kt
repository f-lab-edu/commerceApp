package com.example.commerceapp.domain.extension

import com.example.commerceapp.domain.model.common.DataError
import com.example.commerceapp.domain.model.common.DataError.NETWORK
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.ResultEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toErrorType(): DataError {
    return when (this) {
        is HttpException -> this.mapToErrorType(code())
        is ConnectException, is SocketTimeoutException, is UnknownHostException -> NETWORK.NO_INTERNET
        else -> NETWORK.UNKNOWN
    }
}

fun HttpException.mapToErrorType(code: Int): DataError = when (code) {
    400 -> NETWORK.INVALID
    401 -> NETWORK.UNAUTHORIZED
    404 -> NETWORK.NOT_FOUND
    403 -> NETWORK.FORBIDDEN
    409 -> NETWORK.CONFLICT
    500 -> NETWORK.SERVER_ERROR
    else -> NETWORK.UNKNOWN
}

fun <D> Flow<D>.mapToResultEntity(handler: ErrorHandler): Flow<ResultEntity<D, Error>> =
    map {
        try {
            ResultEntity.Success<D, Error>(it)
        } catch (t: Throwable) {
            handler.handle<D, Error>(t)
        }
    }