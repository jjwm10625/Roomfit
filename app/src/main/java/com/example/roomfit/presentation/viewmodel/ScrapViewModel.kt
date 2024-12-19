package com.example.roomfit.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class ScrapItem(
    val userName: String,
    val postTitle: String,
    val postContent: String,
    val profileImageRes: Int,
    val routeKey: String
)


class ScrapViewModel : ViewModel() {
    val scrapList = mutableStateListOf<ScrapItem>()

    fun addScrap(item: ScrapItem) {
        if (!scrapList.contains(item)) {
            scrapList.add(item)
        }
    }
}
