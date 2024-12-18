package com.example.roomfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomfit.ui.theme.*

@Composable
fun ScrapMateCard(
    navController: NavController,
    userName: String,
    postTitle: String,
    postContent: String,
    profileImageRes: Int,
    onDetailClick: () -> Unit // 상세보기 버튼 클릭 시
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = OffWhite)
            .padding(16.dp)
            .wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.height(6.dp))

        // User Profile
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = profileImageRes),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(16.dp))

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

                    Text(text = "♀")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Post Title
        Text(
            text = postTitle,
            style = bodyDetail,
            color = ComponentBeige
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 상세보기 버튼
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onDetailClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BtnBeige)
            ) {
                Text(
                    text = "상세보기",
                    color = BtnBlack,
                    style = LoginButton.copy(fontFamily = mulishBold)
                )
            }
        }
    }
}
