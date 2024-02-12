//package com.example.commerceapp.domain.usecases.item
//
//import com.example.commerceapp.domain.Result
//import com.example.commerceapp.data.remote.request.ItemSearchRequest
//import com.example.commerceapp.domain.repository.ItemRepository
//import com.example.commerceapp.domain.enntity.ItemEntity
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.map
//import javax.inject.Inject
//
//class GetItemsDetailsUseCase @Inject constructor(private val itemRepository: ItemRepository) {
//    suspend operator fun invoke(request: ItemSearchRequest): Flow<Result<ItemEntity>> = flow {
//        try {
//            itemRepository.getItemDetail(request.requestToMap()).map {
//                emit(Result.Success(it.mapToItemEntity()))
//            }
//        } catch (e: Exception) {
//
//        }
//    }
//}