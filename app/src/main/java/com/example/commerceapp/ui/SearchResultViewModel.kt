package com.example.commerceapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerceapp.domain.model.common.ResultEntity
import com.example.commerceapp.domain.model.common.request.ProductSearchParam
import com.example.commerceapp.domain.model.product.ProductPreview
import com.example.commerceapp.domain.usecases.product.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val searchProduct: SearchProductsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState("", false, emptyList()))
    val uiState: StateFlow<SearchUiState> = _uiState

    var searchJob: Job? = null

    fun searchByKeyword(
        keyword: String = "",
        page: Int = 1
    ) {
        _uiState.value = uiState.value.copy(query = keyword)
        if (keyword.isNullOrBlank()) return
        searchJob?.cancel()
        _uiState.value = uiState.value.copy(isLoading = true)
        searchJob = viewModelScope.launch {
            searchProduct.invoke(
                ProductSearchParam(
                    keyword = keyword,
                    page = page,
                    pagePerSize = 20
                )
            ).collectLatest {
                when (it) {
                    is ResultEntity.Success -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            products = it.data.chunked(2)
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

    data class SearchUiState(
        val query: String,
        val isLoading: Boolean,
        val products: List<List<ProductPreview>>,
    )
}


