package com.example.roomfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomfit.R
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.ui.theme.bodyWriting

@Composable
fun ChatRow(navController: NavController, userName: String, userDepartment: String, modifier: Modifier = Modifier) {
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
                painter = painterResource(id = R.drawable.profile_image), // 프로필 이미지
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
                    text = userDepartment,
                    style = bodyWriting,
                    color = ComponentBeige
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatRowPreview() {
    // 더미 데이터를 사용하여 프리뷰 표시
    ChatRow(navController = NavController(LocalContext.current), userName = "김채현", userDepartment = "컴퓨터공학과 3학년")
}