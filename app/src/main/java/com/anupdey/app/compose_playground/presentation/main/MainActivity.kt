package com.anupdey.app.compose_playground.presentation.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.anupdey.app.compose_playground.presentation.NavGraphs
import com.anupdey.app.compose_playground.presentation.destinations.MainScreenDestination
import com.anupdey.app.compose_playground.ui.theme.StockMarketAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketAppTheme {
                // A surface container using the 'background' color from the theme
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()
                val currentDestination by remember {
                    mutableStateOf(navController.currentDestination?.route)
                }
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            AppBar(
                                isShowDrawer = currentDestination != MainScreenDestination.route,
                                onNavigationIconClick = { isShowDrawer ->
                                    if (isShowDrawer) {
                                        coroutineScope.launch {
                                            scaffoldState.drawerState.open()
                                        }
                                    } else {
                                        navController.popBackStack()
                                    }
                                }
                            )
                        },
                        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                        drawerContent = {
                            DrawerHeader()
                            DrawerBody(
                                items = menuList(),
                                onItemClick = {
                                    handleMenuClick(it)
                                }
                            )
                        },
                        content = {
                            DestinationsNavHost(
                                navGraph = NavGraphs.root,
                                navController = navController
                            )
                        }
                    )
                }
            }
        }
    }

    private fun menuList(): List<MenuItem> {
        return listOf(
            MenuItem("home", "Title", Icons.Default.Home),
            MenuItem("settings", "Settings", Icons.Default.Settings),
            MenuItem("about", "About", Icons.Default.Info)
        )
    }

    private fun handleMenuClick(item: MenuItem) {
        when (item.id) {
            "home" -> {
                Timber.d("navMenuDebug ${item.id}")
            }
            "settings" -> {
                Timber.d("navMenuDebug ${item.id}")
            }
            "about" -> {
                Timber.d("navMenuDebug ${item.id}")
            }
        }
    }

}