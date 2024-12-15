package com.example.roomfit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomfit.R
import com.example.roomfit.presentation.mate.DetailItem
import com.example.roomfit.presentation.mate.UserInfo
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.bodyDetail

@Composable
fun UserCard(
    navController: NavController,
    username: String,
    gender: String,
    school: String,
    budget: String,
    houseType: String,
    numberOfResidents: String,
    durationOfStay: String,
    lifestyle: String,
    smoking: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(16.dp))
            .background(color = OffWhite)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(6.dp))

        UserInfo2(
            lifestyle = lifestyle,
            smoking = smoking,
            numberOfResidents = numberOfResidents,
            budget = budget
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("user_edit")
            },
            colors = ButtonDefaults.buttonColors(containerColor = BtnBeige),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "내 정보 수정", style = bodyDetail, color = BtnBlack)
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun UserInfo2(
    lifestyle: String,
    smoking: String,
    numberOfResidents: String,
    budget: String
) {
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
                iconLabel = lifestyle,
                labelStyle = com.example.roomfit.ui.theme.UserInfo
            )
        }
        item {
            DetailItem(
                iconRes = R.drawable.smoking,
                iconLabel = smoking,
                labelStyle = com.example.roomfit.ui.theme.UserInfo
            )
        }
        item {
            DetailItem(
                iconRes = R.drawable.people,
                iconLabel = numberOfResidents,
                labelStyle = com.example.roomfit.ui.theme.UserInfo
            )
        }
        item {
            DetailItem(
                iconRes = R.drawable.budget,
                iconLabel = budget,
                labelStyle = com.example.roomfit.ui.theme.UserInfo
            )
        }
    }
}
