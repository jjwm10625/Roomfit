package com.example.roomfit

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.net.Uri

data class PostItem(val title: String, val content: String)

class PostViewModel : ViewModel() {
    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var location by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)
    var posts = mutableStateListOf<PostItem>()

    fun savePost(
        newTitle: String,
        newContent: String,
        newLocation: String,
        newImageUri: Uri?
    ) {
        title = newTitle
        content = newContent
        location = newLocation
        imageUri = newImageUri
        posts.add(PostItem(newTitle, newContent))
    }
}