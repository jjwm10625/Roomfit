package com.example.roomfit.presentation.mate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomfit.R
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.ui.theme.bodyWriting

@Composable
fun UserProfile(
    name: String,
) {
    var userName by remember { mutableStateOf(name) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(OffWhite)
    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
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

            }
        }
    }
}


@Preview
@Composable
fun PreviewUserProfile() {
    UserProfile(
        name = "김채현"
    )
}