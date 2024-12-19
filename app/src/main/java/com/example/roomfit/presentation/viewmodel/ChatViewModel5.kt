// ChatViewModel5.kt
package com.example.roomfit.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel5 : ViewModel() {
    private val _messages = MutableStateFlow<List<Pair<String, Boolean>>>(emptyList())
    val messages: StateFlow<List<Pair<String, Boolean>>> = _messages

    fun addMessage(message: String, isMine: Boolean) {
        _messages.value = _messages.value + Pair(message, isMine)
    }
}