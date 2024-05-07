package com.example.commerceapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.common.request.ProductSearchParam
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.usecases.product.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchProduct: SearchProductsUseCase
) : ViewModel() {

    private val _eventProducts = MutableStateFlow<List<ProductPreview>>(emptyList())
    private val _products = MutableStateFlow<List<ProductPreview>>(emptyList())

    val homeUiState: StateFlow<HomeUiState> =
        combine(_eventProducts, _products) { events, products ->
            HomeUiState(isLoading = false, products = products, eventProducts = events)
        }.stateIn(
            scope = viewModelScope,
            initialValue = HomeUiState(
                true,
                emptyList(),
                emptyList()
            ),
            started = SharingStarted.WhileSubscribed()
        )

    init {
        fetchProducts()
        fetchEventProducts()
    }

    private fun fetchProducts(keyword: String = "", page: Int = 1) {
        val param = ProductSearchParam(
            keyword = keyword,
            page = page,
            pagePerSize = 20
        )
        handleFlowResponse(
            searchProduct,
            param,
            { _products.value = it },
            { /* 에러 처리*/ }
        )
    }

    private fun fetchEventProducts() {
        val param = ProductSearchParam(
            keyword = "샤이닝홈",
            page = 1,
            pagePerSize = 5
        )

        handleFlowResponse(
            searchProduct,
            param,
            { _eventProducts.value = it },
            { /* 에러 처리*/ }
        )
    }

    private fun handleFlowResponse(
        useCase: SearchProductsUseCase,
        parameter: ProductSearchParam,
        onSuccess: (List<ProductPreview>) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            useCase.invoke(parameter).collectLatest {
                when (it) {
                    is ResultEntity.Success -> { onSuccess(it.data) }
                    is ResultEntity.Error -> { onError(it.message) }
                }
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean,
    val products: List<ProductPreview>,
    val eventProducts: List<ProductPreview>
)