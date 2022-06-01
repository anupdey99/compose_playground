package com.anupdey.app.compose_playground.presentation.main

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.anupdey.app.compose_playground.R


@Composable
fun AppBar(
    isShowDrawer: Boolean,
    onNavigationIconClick: (isOpenDrawer: Boolean) -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavigationIconClick(isShowDrawer)
                }) {
                Icon(
                    imageVector = if (isShowDrawer) Icons.Default.Menu else Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    )

}