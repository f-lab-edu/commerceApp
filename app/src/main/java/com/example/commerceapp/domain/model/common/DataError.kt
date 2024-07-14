package com.example.commerceapp.domain.model.common

sealed interface DataError : Error {
    enum class NETWORK : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,
        INVALID,
        UNAUTHORIZED,
        NOT_FOUND,
        FORBIDDEN,
        CONFLICT
    }

    // 데이터 오류
    enum class VALIDATION : DataError {
        INVALID_FORMAT, // 데이터 형식 오류
        MISSING_VALUE, //필수 값이 누락되었습니다
        UNEXPECTED_VALUE, // 예상치 못한 값
        EMPTY,
        UNKNOWN
    }
}