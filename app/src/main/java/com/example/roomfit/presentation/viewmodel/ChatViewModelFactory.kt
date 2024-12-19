// ChatViewModelFactory.kt
package com.example.roomfit.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChatViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel2::class.java)) {
            return ChatViewModel2() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}