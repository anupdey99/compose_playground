package com.anupdey.app.compose_playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.anupdey.app.compose_playground.presentation.stock_market.NavGraphs
import com.anupdey.app.compose_playground.presentation.circular_progress_bar.CircularProgressBar
import com.anupdey.app.compose_playground.presentation.music_knob.MusicControl
import com.anupdey.app.compose_playground.ui.theme.StockMarketAppTheme
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
                    ComposeUI()
                }
            }
        }
    }

    @Composable
    private fun ComposeUI() {
        //play Stock market
        //DestinationsNavHost(navGraph = NavGraphs.root)

        //play Music control
        //MusicControl()

        //play CircularProgressBar
        CircularProgressBar(0.75f, 100)
    }
}