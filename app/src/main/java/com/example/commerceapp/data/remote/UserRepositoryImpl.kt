package com.example.commerceapp.data.remote

import com.example.commerceapp.data.remote.model.UserDto
import com.example.commerceapp.data.remote.model.UserPreviewDto
import com.example.commerceapp.domain.model.common.request.AddresseeParam
import com.example.commerceapp.domain.model.common.request.UserUpdateParam
import com.example.commerceapp.domain.model.user.Addressee
import com.example.commerceapp.domain.model.user.User
import com.example.commerceapp.domain.model.user.UserPreview
import com.example.commerceapp.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    UserRepository {

    override suspend fun getMyInfo(id: String): Flow<User> {
        return firestore.collection("users").whereEqualTo("id", id).snapshots()
            .mapNotNull { it.toObjects(UserDto::class.java).firstOrNull()?.mapToEntity() }
    }


    override suspend fun getMyPreviewInfo(id: String): Flow<UserPreview> {
        return firestore.collection("users").whereEqualTo("id", id).snapshots()
            .mapNotNull { it.toObjects(UserPreviewDto::class.java).firstOrNull()?.mapToEntity() }
    }

    /**
     * TODO : 추후 구현 예정
     */
    override suspend fun updateMyInfo(param: UserUpdateParam): Flow<User> {
        TODO("Not yet implemented")
    }

    override suspend fun addAddressee(param: AddresseeParam): Flow<Addressee> {
        TODO("Not yet implemented")
    }
}