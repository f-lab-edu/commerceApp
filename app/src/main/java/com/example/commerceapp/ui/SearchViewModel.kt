package com.example.commerceapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commerceapp.domain.model.common.ResultEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.time.Duration
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _recent = MutableStateFlow<List<String>>(emptyList())
    private val _recommends = MutableStateFlow<List<String>>(emptyList())
    val uiState: StateFlow<SearchUiState> =
        combine(_recent, _recommends) { recent, recommends ->
            SearchUiState(
                isLoading = false,
                recent = recent,
                recommends = recommends
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = SearchUiState(
                true,
                emptyList(),
                emptyList()
            ),
            started = SharingStarted.WhileSubscribed()
        )


    private val flowRecommends = flow<ResultEntity<List<String>>> {
        delay(Duration.ofSeconds(1))
        emit(ResultEntity.Success(listOf("암막커튼", "마켓비", "모니터받침대", "책상", "장스탠드")))
    }
    private val flowRecent = flow<ResultEntity<List<String>>> {
        delay(Duration.ofSeconds(1))
        emit(ResultEntity.Success(listOf("팜레스트", "러그", "쓰레기통", "블라인드")))
    }


    init {
        fetchRecent()
        fetchRecommends()
    }

    private fun fetchRecommends() {
        handleFlowResponse(
            flowRecommends,
            { _recommends.value = it },
            { /* 에러 처리*/ },
            viewModelScope
        )
    }

    private fun fetchRecent() {
        handleFlowResponse(
            flowRecent,
            { _recent.value = it },
            { /* 에러 처리*/ },
            viewModelScope
        )
    }

    private fun handleFlowResponse(
        flow: Flow<ResultEntity<List<String>>>,
        onSuccess: (List<String>) -> Unit,
        onError: (String) -> Unit,
        scope: CoroutineScope
    ) {
        scope.launch {
            flow.collectLatest {
                when (it) {
                    is ResultEntity.Success -> {
                        onSuccess(it.data)
                    }

                    is ResultEntity.Error -> {
                        onError(it.message)
                    }
                }
            }
        }
    }

    data class SearchUiState(
        val isLoading: Boolean,
        val recommends: List<String>,
        val recent: List<String>,
    )
}
