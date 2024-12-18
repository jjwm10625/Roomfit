package com.example.roomfit.presentation.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.ScrapItem
import com.example.roomfit.ScrapViewModel
import com.example.roomfit.presentation.mate.DetailItem
import com.example.roomfit.ui.theme.*
import com.example.roomfit.ui.theme.UserInfo

@Composable
fun DetailMateCard5(
    navController: NavController,
    scrapViewModel: ScrapViewModel,
    modifier: Modifier = Modifier,
    userName: String,
    postTitle: String,
    postContent: String,
    profileImageRes: Int? = null // Add this parameter
) {
    val context = LocalContext.current
    val customLoginButtonStyle = LoginButton.copy(fontSize = 16.sp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(OffWhite)
            .padding(16.dp)
    ) {
        // User Profile
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = profileImageRes ?: R.drawable.user_profile), // Use default image if null
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

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = BtnBeige
        )

        // User Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 첫 번째 줄 (아침형, 비흡연자)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.weight(1f)) {
                    DetailItem(
                        iconRes = R.drawable.sun,
                        iconLabel = "저녁형",
                        labelStyle = UserInfo
                    )
                }

                Row(modifier = Modifier.weight(1f)) {
                    DetailItem(
                        iconRes = R.drawable.smoking,
                        iconLabel = "비흡연자",
                        labelStyle = UserInfo
                    )
                }
            }

            // 두 번째 줄 (2명, 1000만~3000만)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.weight(1f)) {
                    DetailItem(
                        iconRes = R.drawable.people,
                        iconLabel = "3명",
                        labelStyle = UserInfo
                    )
                }

                Row(modifier = Modifier.weight(1f)) {
                    DetailItem(
                        iconRes = R.drawable.budget,
                        iconLabel = "3000~5000만",
                        labelStyle = UserInfo
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = BtnBeige
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Post Title
        Text(
            text = postTitle,
            style = bodyDetail,
            color = ComponentBeige
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Post Content
        Text(
            text = postContent,
            style = bodyWriting.copy(color = ComponentBeige),
            color = ComponentBeige
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bookmark Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .weight(1f),
                onClick = {
                    scrapViewModel.addScrap(
                        ScrapItem(
                            userName, postTitle, postContent,
                            profileImageRes ?: R.drawable.dum_profile_5,
                            routeKey = "detailmatecard5"
                        )
                    )
                    Toast.makeText(context, "찜 되었습니다!", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BtnBeige
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "찜하기",
                    color = Black,
                    style = customLoginButtonStyle
                )
            }

            // Chat Button
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .weight(1f),
                onClick = { /* 채팅 연결 */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BtnBlack
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "채팅하기",
                    color = White,
                    style = customLoginButtonStyle
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailMateCard5Preview() {
    DetailMateCard5(
        navController = rememberNavController(),
        scrapViewModel = ScrapViewModel(),
        userName = "전도연",
        postTitle = "서울대입구역 도보 5분이내 방 구합니다",
        postContent = "기숙사 모집에 떨어져서 글 남깁니다. " +
                "\n3월 1일 입주 희망합니다. 계약 시기에 따라 일정은 조율 가능해요.",
        profileImageRes = R.drawable.dum_profile_5
    )
}