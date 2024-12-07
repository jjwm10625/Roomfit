// ChatViewModel.kt
package com.example.roomfit.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("chat_prefs", Context.MODE_PRIVATE)
    private val _messages = MutableStateFlow<List<Pair<String, Boolean>>>(loadMessages())
    val messages: StateFlow<List<Pair<String, Boolean>>> = _messages

    fun addMessage(message: String, isMine: Boolean) {
        viewModelScope.launch {
            val updatedMessages = _messages.value + Pair(message, isMine)
            _messages.value = updatedMessages
            saveMessages(updatedMessages)
        }
    }

    private fun loadMessages(): List<Pair<String, Boolean>> {
        val jsonString = sharedPreferences.getString("messages", "[]")
        val jsonArray = JSONArray(jsonString)
        val messages = mutableListOf<Pair<String, Boolean>>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val message = jsonObject.getString("message")
            val isMine = jsonObject.getBoolean("isMine")
            messages.add(Pair(message, isMine))
        }
        return messages
    }

    private fun saveMessages(messages: List<Pair<String, Boolean>>) {
        val jsonArray = JSONArray()
        for ((message, isMine) in messages) {
            val jsonObject = JSONObject()
            jsonObject.put("message", message)
            jsonObject.put("isMine", isMine)
            jsonArray.put(jsonObject)
        }
        sharedPreferences.edit().putString("messages", jsonArray.toString()).apply()
    }
}