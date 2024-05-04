package com.example.commerceapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.common.request.ProductSearchParam
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.usecases.product.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchProduct: SearchProductsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState(false, emptyList(), emptyList()))
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchProducts()
        fetchEventProducts()
    }

    private fun fetchProducts(keyword: String = "", page: Int = 1) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(isLoading = true)
            searchProduct.invoke(
                ProductSearchParam(
                    keyword = keyword,
                    page = page,
                    pagePerSize = 20
                )
            ).collectLatest {
                when (it) {
                    is ResultEntity.Success -> {
                        Log.d("HomeViewModel", "Success ${it.data.size}")
                        Log.d("HomeViewModel", "item ${it.data.get(0)}")
                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            products = it.data
                        )
                    }

                    is ResultEntity.Error -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun fetchEventProducts() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(isLoading = true)
            searchProduct.invoke(
                ProductSearchParam(
                    keyword = "샤이닝홈",
                    page = 1,
                    pagePerSize = 5
                )
            ).collectLatest {
                when (it) {
                    is ResultEntity.Success -> {
                        Log.d("HomeViewModel", "Success ${it.data.size}")
                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            eventProducts = it.data
                        )
                    }

                    is ResultEntity.Error -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}

data class UiState(
    val isLoading: Boolean,
    val products: List<ProductPreview>,
    val eventProducts: List<ProductPreview>
)