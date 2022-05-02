package com.anupdey.app.compose_playground.presentation.paging

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anupdey.app.compose_playground.presentation.stock_market.company_listings.CompanyItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PagingScreen(
    navigator: DestinationsNavigator,
    viewModel: PagingViewModel = hiltViewModel()
) {

    val state = viewModel.state
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.companies.size) { i ->
            val company = state.companies[i]
            if (i >= state.companies.lastIndex && !state.endReached && !state.isLoading) {
                viewModel.getCompanyListPaging()
            }
            CompanyItem(company = company, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
            if (i < state.companies.size) {
                Divider(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    )
                )
            }
        }
        item {
            if (state.isLoading && state.companies.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading && state.companies.isEmpty()) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
    }


}