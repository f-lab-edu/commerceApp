package com.example.commerceapp.data.remote

import com.example.commerceapp.domain.model.common.RequestParam
import com.example.commerceapp.domain.model.user.User
import com.example.commerceapp.domain.model.user.UserPreview
import com.example.commerceapp.domain.repository.UserRepository
import com.example.commerceapp.domain.response.Response
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    UserRepository<UserRepositoryImpl.UserParam> {

    class UserParam(val id: String) : RequestParam

    override fun getMyInfo(requestParam: UserParam): Flow<User> {
        return firestore.collection("users").whereEqualTo("id", requestParam.id).snapshots()
            .mapNotNull { it.toObjects(User::class.java).firstOrNull() }
    }


    override fun getMyPreviewInfo(requestParam: UserParam): Flow<UserPreview> {
        return firestore.collection("users").whereEqualTo("id", requestParam.id).snapshots()
            .mapNotNull { it.toObjects(UserPreview::class.java).firstOrNull() }
    }

    /**
     * TODO : 추후 구현 예정
     */
    override fun updateMyInfo(requestParam: RequestParam): Flow<Response> {
        TODO("Not yet implemented")
    }

    override fun addAddressee(requestParam: RequestParam): Flow<Response> {
        TODO("Not yet implemented")
    }
}