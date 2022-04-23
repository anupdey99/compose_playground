package com.anupdey.app.compose_playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anupdey.app.compose_playground.destinations.CoinListScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator
) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                navigator.navigate(CoinListScreenDestination())
            },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = "CryptoCurrency")
        }
    }

    //play Stock market
    //DestinationsNavHost(navGraph = NavGraphs.root)

    //play Music control
    //MusicControl()

    //play CircularProgressBar
    //CircularProgressBar(0.75f, 100)

    //play crypto coins

    //CoinListScreen(navGraph = NavGraphs.root)
}