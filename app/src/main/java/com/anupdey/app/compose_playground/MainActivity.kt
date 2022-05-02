package com.anupdey.app.compose_playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.anupdey.app.compose_playground.ui.theme.LocalSpacing
import com.anupdey.app.compose_playground.ui.theme.StockMarketAppTheme
import com.anupdey.app.compose_playground.ui.theme.spacing
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}