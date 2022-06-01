package com.anupdey.app.compose_playground.presentation.currencylayer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalComposeUiApi
@Destination
@Composable
fun CurrencyConvertScreen(
    navigator: DestinationsNavigator,
    viewModel: CurrencyLayerViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val keyboardController = LocalSoftwareKeyboardController.current
    val (focusRequester) = FocusRequester.createRefs()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.TopStart), contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
            Text(
                text = "Choose currency to convert",
                fontSize = 20.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp)
            )

            SpinnerComponent(modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .background(Color.LightGray),
                list = state.list,
                onClick = {
                    viewModel.onEvent(UIEvent.CurrencyChanged(it.currency))
                })

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = if (state.amount > 0.0) {
                    state.amount.toString()
                } else "",
                onValueChange = {
                    if (it.isNotEmpty()) {
                        viewModel.onEvent(UIEvent.AmountChanged(it.toDoubleOrNull() ?: 0.0))
                    }
                }, modifier = Modifier.fillMaxWidth()
                    .height(56.dp),
                placeholder = {
                    Text(text = "Type amount e.g 100.0", fontSize = 12.sp)
                },
                label = {
                    Text(text = "Amount")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    //focusRequester.freeFocus()
                }),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.onEvent(UIEvent.Submit)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(text = "Convert")
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.conversionList) { currency ->
                    Text(
                        text = "${currency.currency}: (${currency.conversion})",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                            .padding(16.dp, 8.dp)
                            .clickable {

                            },
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 18.sp,
                    )
                }
            }
        }

        if (!state.error.isNullOrEmpty()) {
            Text(
                text = state.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center), color = MaterialTheme.colors.error, textAlign = TextAlign.Center, fontSize = 16.sp
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}