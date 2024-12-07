package com.example.roomfit.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomfit.R
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.UserTitle

@Composable
fun ChatScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 상단 제목 및 뒤로가기 버튼
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 16.dp)
            ) {
                IconButton(
                    onClick = { navController.navigate("message") },
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.backbutton),
                        contentDescription = "Back Button"
                    )
                }
                Text(
                    text = "내 프로필",
                    style = UserTitle,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

        }
    }
}