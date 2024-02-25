package com.example.commerceapp.domain.extension

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.commerceapp.domain.model.ErrorType
import com.example.commerceapp.domain.model.ErrorType.CONFLICT
import com.example.commerceapp.domain.model.ErrorType.FORBIDDEN
import com.example.commerceapp.domain.model.ErrorType.INVALID
import com.example.commerceapp.domain.model.ErrorType.NETWORK_ERROR
import com.example.commerceapp.domain.model.ErrorType.NOT_FOUND
import com.example.commerceapp.domain.model.ErrorType.SERVER_ERROR
import com.example.commerceapp.domain.model.ErrorType.UNAUTHORIZED
import com.example.commerceapp.domain.model.ErrorType.UNKNOWN_ERROR
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun Throwable.toErrorType(): ErrorType {
    return when (this) {
        is HttpException -> when (code()) {
            400 -> INVALID
            401 -> UNAUTHORIZED
            404 -> NOT_FOUND
            403 -> FORBIDDEN
            409 -> CONFLICT
            500 -> SERVER_ERROR
            else -> UNKNOWN_ERROR
        }

        is ConnectException, is SocketTimeoutException, is UnknownHostException -> NETWORK_ERROR
        else -> UNKNOWN_ERROR
    }
}