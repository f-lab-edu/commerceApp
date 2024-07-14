package com.example.commerceapp.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.common.request.ProductSearchParam
import com.example.commerceapp.domain.model.product.Product
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.usecases.product.GetProductUseCase
import com.example.commerceapp.domain.usecases.product.SearchProductsUseCase
import com.example.commerceapp.ui.dummyProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val searchProduct: SearchProductsUseCase,
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
                    val lastRightBracketIndex = it.name.lastIndexOf('[')
                    val extractedText =
                        it.name.substring(lastRightBracketIndex + 1, it.name.indexOf(']'))
                    getRelated(extractedText)
                },
                {
                    productUiState.value = productUiState.value.copy(isLoading = ProductState.ERROR)
                }
            )
        }
    }

    private fun getRelated(name: String) {
        viewModelScope.launch {
            handleFlowResponse<List<ProductPreview>>(
                searchProduct.invoke(ProductSearchParam(keyword = name, pagePerSize = 5)),
                {
                    val resultState =
                        if (it.isEmpty()) ProductState.EMPTY_RESULT else ProductState.SUCCESS
                    _productUiState.value = productUiState.value.copy(
                        isLoading = resultState,
                        relatedProducts = it
                    )
                },
                {
                    productUiState.value = productUiState.value.copy(isLoading = ProductState.ERROR)
                }
            )
        }
    }

    private suspend fun <T> handleFlowResponse(
        flow: Flow<ResultEntity<T>>,
        onSuccess: (T) -> Unit,
        onError: (ResultEntity.Error<T>) -> Unit
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
        val product: Product = dummyProduct,
        val relatedProducts: List<ProductPreview> = emptyList()
    )

    enum class ProductState {
        LOADING,
        EMPTY_RESULT,
        SUCCESS,
        ERROR
    }
}