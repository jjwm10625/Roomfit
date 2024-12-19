package com.example.roomfit.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.net.Uri

data class PostItem(
    val mateorroom: String,
    val title: String,
    val content: String,
    val location: String,
    val imageUri: Uri?
)

class PostViewModel : ViewModel() {
    var mateorroom by mutableStateOf("")
    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var location by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)
    var posts = mutableStateListOf<PostItem>()

    fun savePost(
        newMateOrRoom: String,
        newTitle: String,
        newContent: String,
        newLocation: String,
        newImageUri: Uri?
    ) {
        mateorroom = newMateOrRoom
        title = newTitle
        content = newContent
        location = newLocation
        imageUri = newImageUri
        posts.add(
            PostItem(newMateOrRoom, newTitle, newContent, newLocation, newImageUri)
        )
    }

    fun deletePost(post: PostItem) {
        posts.remove(post)
    }
}