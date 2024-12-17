package com.example.roomfit.presentation.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.roomfit.presentation.mate.DetailItem
import com.example.roomfit.presentation.mate.UserInfo
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.LoginButton
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.White
import com.example.roomfit.ui.theme.bodyDetail

@Composable
fun ScrapMateCard(
    navController: NavController,
    modifier: Modifier = Modifier,
    userName: String,
    postTitle: String,
    onCancel: () -> Unit
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
                painter = painterResource(id = R.drawable.profile_image),
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

        Spacer(modifier = Modifier.height(24.dp))

        // Post Title
        Text(
            text = postTitle,
            style = bodyDetail,
            color = ComponentBeige
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = BtnBeige
        )

        // User Info
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                DetailItem(
                    iconRes = R.drawable.sun,
                    iconLabel = "아침형",
                    labelStyle = com.example.roomfit.ui.theme.UserInfo
                )
            }
            item {
                DetailItem(
                    iconRes = R.drawable.smoking,
                    iconLabel = "비흡연자",
                    labelStyle = com.example.roomfit.ui.theme.UserInfo
                )
            }
            item {
                DetailItem(
                    iconRes = R.drawable.people,
                    iconLabel = "2명",
                    labelStyle = com.example.roomfit.ui.theme.UserInfo
                )
            }
            item {
                DetailItem(
                    iconRes = R.drawable.budget,
                    iconLabel = "1000만~5000만",
                    labelStyle = com.example.roomfit.ui.theme.UserInfo
                )
            }
        }

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
                    Toast.makeText(context, "취소되었습니다!", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BtnBeige
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "찜하기 취소",
                    color = Black,
                    style = customLoginButtonStyle
                )
            }

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


@Preview
@Composable
fun ScrapMateCardPreview() {
    ScrapMateCard(
        navController = rememberNavController(),
        userName = "조영서",
        postTitle = "17평형 정문 근처 룸 쉐어 구합니다",
        onCancel = {}
    )
}