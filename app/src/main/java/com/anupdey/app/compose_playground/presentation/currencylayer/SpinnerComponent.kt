package com.anupdey.app.compose_playground.presentation.currencylayer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anupdey.app.compose_playground.domain.model.currencylayer.CurrencyListing

@Composable
fun SpinnerComponent(
    modifier: Modifier,
    list: List<CurrencyListing>,
    onClick: (CurrencyListing) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedText by remember {
        mutableStateOf("Select currency")
    }
    Box(modifier, contentAlignment = Alignment.Center) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    expanded = !expanded
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            ) {

            Text(text = selectedText, fontSize = 16.sp, modifier = Modifier.padding(end = 8.dp).weight(1.0f))
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.fillMaxWidth()) {
                list.forEach { currency ->
                    DropdownMenuItem(onClick = {
                        onClick.invoke(currency)
                        expanded = false
                        selectedText = "${currency.currency} (${currency.description})"
                    }) {
                        Text(text = "${currency.currency} (${currency.description})")
                    }
                }
            }
        }
    }
}