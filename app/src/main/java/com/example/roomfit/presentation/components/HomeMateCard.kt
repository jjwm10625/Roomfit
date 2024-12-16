package com.example.roomfit.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.presentation.mate.DetailItem
import com.example.roomfit.presentation.mate.UserInfo
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.LoginButton
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.ui.theme.mulishBold

@Composable
fun HomeMateCard(
    navController: NavController,
    userName: String,
    postTitle: String,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = OffWhite)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(6.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        // Post Title
        Text(
            text = postTitle,
            style = bodyDetail,
            color = ComponentBeige
        )

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = BtnBeige
        )

        Spacer(modifier = Modifier.height(8.dp))

        UserInfo()

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("home_mate") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BtnBlack)
            ) {
                Text(
                    text = "mate?",
                    style = LoginButton.copy(fontFamily = mulishBold)
                )
            }
        }
    }
}


@Composable
fun UserInfo() {
    Spacer(modifier = Modifier.height(8.dp)) // 텍스트와 아이콘 섹션 간 여백

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
}

@Composable
fun DetailItem(
    iconRes: Int,
    iconLabel: String,
    labelStyle: androidx.compose.ui.text.TextStyle
) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = ComponentBeige
        )

        Spacer(modifier = Modifier.width(8.dp))

        androidx.compose.material.Text(
            text = iconLabel,
            style = labelStyle, // Use the passed labelStyle
            color = ComponentBeige,
            modifier = Modifier.padding(end = 4.dp)
        )
    }
}

@Preview
@Composable
fun HomeMateCardPreview() {
    HomeMateCard(
        navController = rememberNavController(),
        userName = "김채현",
        postTitle = "17평형 정문 근처 룸 쉐어 구합니다"
    )
}
