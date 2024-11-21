package com.example.roomfit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.bodyWriting

@Composable
fun WriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("글 작성", style = bodyWriting)
    }
}