package com.anupdey.app.compose_playground.presentation.currencylayer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anupdey.app.compose_playground.data.mapper.mapToCurrencyConversionData
import com.anupdey.app.compose_playground.domain.repository.CurrencyLayerRepository
import com.anupdey.app.compose_playground.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CurrencyLayerViewModel @Inject constructor(
    private val repository: CurrencyLayerRepository
) : ViewModel() {

    var state by mutableStateOf(CurrencyListState())

    init {
        fetchCurrencyList()
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.CurrencyChanged -> {
                state = state.copy(source = event.currency)
            }
            is UIEvent.AmountChanged -> {
                state = state.copy(amount = event.amount)
            }
            is UIEvent.Submit -> {
                fetchChargeData()
            }
        }
    }

    private fun fetchCurrencyList() {
        viewModelScope.launch {
            repository.currencyList().collect { result ->
                Timber.d("currencyList $result")
                when (result) {
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.error?.message,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(
                                list = it, isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun fetchChargeData(source: String = "USD") {
        viewModelScope.launch {
            repository.fetchChargeData(source).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        state = state.copy(error = result.error?.message, isLoading = false)
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        result.data?.let {
                            //Timber.d(it.toString())
                            val list = mapToCurrencyConversionData(it, state.amount)
                            state = state.copy(conversionList = list, isLoading = false)
                        }
                    }
                }
            }
        }
    }

}