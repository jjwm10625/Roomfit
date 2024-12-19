// MessageScreen.kt
package com.example.roomfit.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.presentation.viewmodel.ChatViewModel
import com.example.roomfit.presentation.viewmodel.ChatViewModel2
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.BtnGray
import com.example.roomfit.ui.theme.Chat2
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.UserTitle
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.ui.theme.bodyWriting

@Composable
fun MessageScreen(
    navController: NavController,
    lastMessage: String,
    chatViewModel: ChatViewModel = viewModel(),
            chatViewModel2: ChatViewModel2 = viewModel()

) {
    val messagesState = chatViewModel.messages.collectAsState()
    val messages = messagesState.value

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
            // 상단 제목
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "채팅",
                    style = UserTitle,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 마지막 메시지 표시
            if (messages.isNotEmpty()) {
            val lastMessage = messages.last().first
            ChatRow(
                navController = navController, // navController 전달
                userName = "김채현", // 사용자 이름
                lastMessage = lastMessage // 마지막 메시지
            )
        }



        }
    }
}

@Composable
fun ChatRow(navController: NavController, userName: String, lastMessage: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(16.dp))
            .background(color = OffWhite)
            .padding(16.dp)
            .clickable { navController.navigate("chat") } // 클릭 이벤트 추가
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(OffWhite)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dum_profile_1), // 프로필 이미지
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape) // 원형 이미지
                    .background(Color.Gray) // 기본 배경색
            )

            Spacer(modifier = Modifier.width(16.dp)) // 이미지와 텍스트 간 여백

            // 프로필 텍스트
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    Text(
                        text = userName,
                        style = bodyDetail,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = "♀") // 성별 아이콘
                }

                Spacer(modifier = Modifier.height(5.dp)) // 이름과 학과 간 여백

                Text(
                    text = lastMessage,
                    style = bodyWriting,
                    color = ComponentBeige
                )
            }
        }
    }
}

@Composable
fun ChatRow2(navController: NavController, userName: String, lastMessage: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(16.dp))
            .background(color = OffWhite)
            .padding(16.dp)
            .clickable { navController.navigate("chat") } // 클릭 이벤트 추가
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(OffWhite)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dum_profile_2), // 프로필 이미지
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape) // 원형 이미지
                    .background(Color.Gray) // 기본 배경색
            )

            Spacer(modifier = Modifier.width(16.dp)) // 이미지와 텍스트 간 여백

            // 프로필 텍스트
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    Text(
                        text = userName,
                        style = bodyDetail,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = "♀") // 성별 아이콘
                }

                Spacer(modifier = Modifier.height(5.dp)) // 이름과 학과 간 여백

                Text(
                    text = lastMessage,
                    style = bodyWriting,
                    color = ComponentBeige
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessageScreenPreview() {
    MessageScreen(
        navController = rememberNavController(),
        lastMessage = "최근 메시지가 여기에 표시됩니다."
    )
}