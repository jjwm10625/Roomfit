// MessageScreen.kt
package com.example.roomfit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.presentation.components.ChatRow
import com.example.roomfit.presentation.components.ChatRow2
import com.example.roomfit.presentation.components.ChatRow4
import com.example.roomfit.presentation.components.ChatRow5
import com.example.roomfit.presentation.viewmodel.ChatViewModel
import com.example.roomfit.presentation.viewmodel.ChatViewModel2
import com.example.roomfit.presentation.viewmodel.ChatViewModel4
import com.example.roomfit.presentation.viewmodel.ChatViewModel5
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.UserTitle

@Composable
fun MessageScreen(navController: NavController, lastMessage: String, chatViewModel: ChatViewModel = viewModel(),chatViewModel2: ChatViewModel2 = viewModel(),chatViewModel4: ChatViewModel4 = viewModel(), chatViewModel5: ChatViewModel5 = viewModel()) {
    val messages by chatViewModel.messages.collectAsState()
    val messages2 by chatViewModel2.messages.collectAsState()
    val messages4 by chatViewModel4.messages.collectAsState()
    val messages5 by chatViewModel5.messages.collectAsState()



    Column(
        modifier = Modifier
            .background(BackgroundBeige)
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
            Text(
                text = "채팅",
                style = UserTitle,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // 마지막 메시지의 이름만 표시
        if (messages.isNotEmpty()) {
            ChatRow(
                navController = navController,
                userName = "김채현",
                userDepartment = "17평형 숙대 정문 근처 룸 쉐어 구합니다" // 빈 문자열로 설정
            )
        } else {
            Text(text = lastMessage)
        }

        Spacer(modifier = Modifier.height(15.dp))

        // 마지막 메시지의 이름만 표시
        if (messages2.isNotEmpty()) {
            ChatRow2(
                navController = navController,
                userName = "조영서",
                userDepartment = "홍대입구 5분 거리 룸 쉐어 구합니다" // 빈 문자열로 설정
            )
        } else {
            Text(text = lastMessage)
        }

        Spacer(modifier = Modifier.height(15.dp))

        if (messages4.isNotEmpty()) {
            ChatRow4(
                navController = navController,
                userName = "김민지",
                userDepartment = "숙대입구역 근처 방 구합니다" // 빈 문자열로 설정
            )
        } else {
            Text(text = lastMessage)
        }

        Spacer(modifier = Modifier.height(15.dp))

        if (messages5.isNotEmpty()) {
            ChatRow5(
                navController = navController,
                userName = "전도연",
                userDepartment = "서울대입구역 도보 5분이내 방 구합니다" // 빈 문자열로 설정
            )
        } else {
            Text(text = lastMessage)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessageScreenPreview() {
    MessageScreen(navController = rememberNavController(), lastMessage = "마지막 메시지가 없습니다.")
}