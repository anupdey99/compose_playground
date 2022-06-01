package com.anupdey.app.compose_playground.presentation.paging

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anupdey.app.compose_playground.domain.repository.StockRepository
import com.anupdey.app.compose_playground.presentation.stock_market.company_listings.CompanyListingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {

    var state by mutableStateOf(CompanyListingsState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getCompanyListPaging(nextPage, 20)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                companies = state.companies + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )


    init {
        getCompanyListPaging()
    }

    fun getCompanyListPaging() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}