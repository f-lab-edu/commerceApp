package com.example.commerceapp.domain.model.common


class FireStoreNoDataException(override val message: String?):Exception(message)
class InvalidDataException(val type : DataError = DataError.VALIDATION.UNKNOWN):Exception()