// ChatScreen.kt
package com.example.roomfit.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.LoginInput
import com.example.roomfit.ui.theme.UserTitle
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChatScreen(navController: NavController) {
    val currentDate = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) }
    var message by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Pair<String, Boolean>>() }
    val timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

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
                    onClick = { navController.navigate("message/${messages.lastOrNull()?.first ?: ""}") },
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
                style = LoginInput,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 채팅 메시지 표시
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                reverseLayout = true
            ) {
                items(messages) { (msg, isMine) ->
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
                                color = Color.White,
                                modifier = Modifier
                                    .background(
                                        color = BtnBeige,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(16.dp)
                            )
                            Text(
                                text = currentTime,
                                color = BtnBlack,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        // 채팅 메시지 입력 및 전송 버튼
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
                .background(BtnBeige),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {
                if (message.isNotBlank()) {
                    messages.add(Pair(message, true)) // 내가 보낸 메시지
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

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(navController = rememberNavController())
}