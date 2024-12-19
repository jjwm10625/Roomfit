// ChatScreen.kt
package com.example.roomfit.presentation.chatting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.presentation.viewmodel.ChatViewModel
import com.example.roomfit.ui.theme.*
import com.gdg.kakaobank.presentation.navigator.RoomNav
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = viewModel()
) {
    val currentDate = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) }
    var message by remember { mutableStateOf("") }
    val timeFormat = SimpleDateFormat("hh:mm a", Locale.US)
    val messages by chatViewModel.messages.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
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
                    onClick = {
                        // 마지막 메시지를 가져와 MessageScreen에 전달
                        val lastMessage = messages.lastOrNull()?.first ?: "No messages"
                        navController.navigate(RoomNav.Message.route + "?lastMessage=$lastMessage")
                    },
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.backbutton),
                        contentDescription = "Back Button"
                    )
                }
                Text(
                    text = "채팅",
                    style = UserTitle,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 오늘의 날짜 표시
            Text(
                text = currentDate,
                style = Chat1,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 채팅 메시지 표시
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                reverseLayout = false
            ) {
                items(messages) { messagePair ->
                    val (msg, isMine) = messagePair
                    val currentTime = timeFormat.format(Date())
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        contentAlignment = if (isMine) Alignment.CenterEnd else Alignment.CenterStart
                    ) {
                        Column(
                            horizontalAlignment = if (isMine) Alignment.End else Alignment.Start
                        ) {
                            Text(
                                text = msg,
                                style = Chat2,
                                color = BtnBeige,
                                modifier = Modifier
                                    .background(
                                        color = BtnBlack,
                                        shape = RoundedCornerShape(22.dp)
                                    )
                                    .padding(horizontal = 25.dp, vertical = 15.dp)
                            )
                            Text(
                                text = currentTime,
                                color = BtnGray,
                                modifier = Modifier.padding(2.dp, 4.dp, 2.dp, 0.dp)
                            )
                        }
                    }
                }
            }
        }

        // 채팅 메시지 입력 및 전송 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BtnBeige)
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent),
                    shape = RoundedCornerShape(30.dp),
                    textStyle = Chat3,
                    placeholder = { Text("메시지를 입력하세요.", style = Chat2, color = Gray) },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    if (message.isNotBlank()) {
                        chatViewModel.addMessage(message, true)
                        message = ""
                    }
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "Send Button"
                    )
                }
            }
        }
    }
}
