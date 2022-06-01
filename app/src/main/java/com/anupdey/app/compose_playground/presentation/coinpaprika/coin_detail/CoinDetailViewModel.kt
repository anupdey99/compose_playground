package com.anupdey.app.compose_playground.presentation.coinpaprika.coin_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anupdey.app.compose_playground.domain.repository.CoinRepository
import com.anupdey.app.compose_playground.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(CoinDetailState())
    val coinID = "coinId"

    init {
        savedStateHandle.get<String>(coinID)?.let {
            getCoin(it)
        }
    }

    private fun getCoin(coinId: String) {
        viewModelScope.launch {
            repository.getCoinById(coinId).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.error?.message,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        result.data?.let { details ->
                            state = state.copy(coin = details, isLoading = false)
                        }
                    }
                }
            }
        }
    }

}