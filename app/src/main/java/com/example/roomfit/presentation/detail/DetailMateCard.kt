package com.example.roomfit.presentation.detail

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.roomfit.presentation.viewmodel.ScrapItem
import com.example.roomfit.presentation.viewmodel.ScrapViewModel
import com.example.roomfit.presentation.mate.DetailItem
import com.example.roomfit.ui.theme.*
import com.example.roomfit.ui.theme.UserInfo

@Composable
fun DetailMateCard(
    navController: NavController,
    scrapViewModel: ScrapViewModel,
    modifier: Modifier = Modifier,
    userName: String,
    postTitle: String,
    postContent: String,
    profileImageRes: Int? = null
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
                painter = painterResource(id = R.drawable.dum_profile_1),
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
                        iconLabel = "2명",
                        labelStyle = UserInfo
                    )
                }

                Row(modifier = Modifier.weight(1f)) {
                    DetailItem(
                        iconRes = R.drawable.budget,
                        iconLabel = "1000만원~3000만원",
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
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

        // Location
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(OffWhite)
                .clickable { navController.navigate("map?location=37.5445,126.9665") } // 숙명여대

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Location Icon",
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "위치 보기",
                    style = bodyWriting,
                    color = Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bookmark Button
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                            profileImageRes ?: R.drawable.dum_profile_1,
                            routeKey = "detailmatecard"
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
                onClick = { navController.navigate("chat") },
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
}

@Preview
@Composable
fun DetailMateCardPreview() {
    DetailMateCard(
        navController = rememberNavController(),
        scrapViewModel = ScrapViewModel(),
        userName = "김채현",
        postTitle = "17평형 숙대 정문 근처 룸 쉐어 구합니다",
        postContent = "저는 고양이를 키우고 있어서 털 알러지 없는 분들로 받겠습니다!",
        profileImageRes = R.drawable.dum_profile_1
    )
}