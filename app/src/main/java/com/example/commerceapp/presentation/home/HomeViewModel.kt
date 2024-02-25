package com.example.commerceapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerceapp.data.remote.request.ProductSearchRequest
import com.example.commerceapp.domain.enntity.product.ProductDetailEntity
import com.example.commerceapp.domain.enntity.product.ProductEntity
import com.example.commerceapp.domain.usecases.product.GetProductDetailUseCase
import com.example.commerceapp.domain.usecases.product.GetSearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var getSearchProductsUseCase: GetSearchProductsUseCase,
    private var getSearDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<HomeViewModel.UiState> = _uiState

    init {
        viewModelScope.launch {
            delay(1000)
//            getDetail()
            getSearchList(ProductSearchRequest())
        }
    }

    suspend fun getSearchList(request: ProductSearchRequest) {
        viewModelScope.launch {
            Timber.d("getSearchList")
            _uiState.emit(_uiState.value.copy(isLoading = true))

            getSearchProductsUseCase.invoke(request).flowOn(Dispatchers.IO).collect {
                Timber.d("collectLatest")
                _uiState.emit(UiState(isLoading = false, popularItems = it.data ?: emptyList()))
            }
//                .runCatching {
//                _uiState.emit(
//                    _uiState.value.copy(
//                        isLoading = false,
//                        message = "네트워크 오류가 발생했습니다. 더시 시도해 주세요."
//                    )
//                )
//            }
        }
    }

    fun getDetail() {
        viewModelScope.launch {
            getSearDetailUseCase.invoke("1").flowOn(Dispatchers.IO).collect {
                Log.e("TAG", "getDetail ${it.data}")
                _uiState.emit(_uiState.value.copy(searchItems = it.data, isLoading = false))
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val popularItems: List<ProductEntity> = listOf<ProductEntity>(),
        val searchItems: ProductDetailEntity? = null,
        val message: String? = null
    )

}