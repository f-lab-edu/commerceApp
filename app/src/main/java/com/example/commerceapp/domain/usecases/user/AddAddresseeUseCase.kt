package com.example.commerceapp.domain.usecases.user

import com.example.commerceapp.domain.extension.mapToResultEntity
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ErrorHandler
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.common.request.AddresseeParam
import com.example.commerceapp.domain.model.user.Addressee
import com.example.commerceapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class AddAddresseeUseCase(
    private val repository: UserRepository,
    private val userExceptionHandler: ErrorHandler
) {
    suspend operator fun invoke(param: AddresseeParam): Flow<ResultEntity<Addressee, Error>> =
        repository.addAddressee(param).mapToResultEntity(userExceptionHandler)
}