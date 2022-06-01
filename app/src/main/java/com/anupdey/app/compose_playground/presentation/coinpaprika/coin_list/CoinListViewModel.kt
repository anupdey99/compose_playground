package com.anupdey.app.compose_playground.presentation.coinpaprika.coin_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anupdey.app.compose_playground.domain.repository.CoinRepository
import com.anupdey.app.compose_playground.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CoinRepository
): ViewModel() {

    var state by mutableStateOf(CoinListState())

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            repository.getCoins().collect { result ->
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
                        result.data?.let { list ->
                            state = state.copy(coins = list, isLoading = false)
                        }
                    }
                }
            }
        }
    }

}