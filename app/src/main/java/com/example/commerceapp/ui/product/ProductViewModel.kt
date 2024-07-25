package com.example.commerceapp.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerceapp.domain.model.common.Error
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.usecases.product.GetProductUseCase
import com.example.commerceapp.ui.dummyProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {
    private var _productUiState: MutableStateFlow<ProductUiState> =
        MutableStateFlow(ProductUiState())
    val productUiState: MutableStateFlow<ProductUiState> = _productUiState

    fun getProduct(id: String) {
        viewModelScope.launch {
            handleFlowResponse(
                getProductUseCase.invoke(id),
                {
                    _productUiState.value =
                        productUiState.value.copy(isLoading = ProductState.SUCCESS, product = it)
                },
                {
                    productUiState.value = productUiState.value.copy(isLoading = ProductState.ERROR)
                }
            )
        }
    }

    private suspend fun <T> handleFlowResponse(
        flow: Flow<ResultEntity<T, Error>>,
        onSuccess: (T) -> Unit,
        onError: (ResultEntity.Error<T, Error>) -> Unit
    ) {
        flow.collectLatest {
            when (it) {
                is ResultEntity.Success -> {
                    onSuccess(it.data)
                }

                is ResultEntity.Error -> {
                    onError(it)
                }
            }
        }
    }

    data class ProductUiState(
        val isLoading: ProductState = ProductState.LOADING,
        val product: Product = dummyProduct
    )

    enum class ProductState {
        LOADING,
        EMPTY_RESULT,
        SUCCESS,
        ERROR
    }
}