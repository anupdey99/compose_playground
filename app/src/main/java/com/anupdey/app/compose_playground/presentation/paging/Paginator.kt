package com.anupdey.app.compose_playground.presentation.paging

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}