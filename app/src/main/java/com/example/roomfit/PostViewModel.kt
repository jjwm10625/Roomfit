package com.example.roomfit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    val postTitle = mutableStateOf("")
    val postContent = mutableStateOf("")
}